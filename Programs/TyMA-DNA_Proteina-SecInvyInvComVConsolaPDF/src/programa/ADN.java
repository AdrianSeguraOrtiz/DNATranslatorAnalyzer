package programa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ADN {

	private String fichero;
	private String secuencia;
	private String secuenciaInv;
	private String secuenciaInvCom;
	private double []  porcentajesSec;
	private double [] porcentajesSecInvCom;
	private float pAT;
	private float pGC;
	private char [] aminoacidos;
	private char [] complementaria;
	private String [] traducciones;
	@SuppressWarnings("rawtypes")
	private List [][] longitudes;
	private String [] longitudesText;
	private DiagramaBarras diagramaBarras;
	private Histograma [] histogramas;

	public ADN(String fichero) {
		
		this.fichero = fichero;
		porcentajesSec = new double [4];
		porcentajesSecInvCom = new double [4];
		aminoacidos = new char [] {'K', 'N', 'K', 'N', 'T', 'T', 'T', 'T', 'R', 'S', 'R', 'S', 'I', 'I', 'M', 'I', 'Q', 'H', 'Q', 'H', 
				'P', 'P', 'P', 'P', 'R', 'R', 'R', 'R', 'L', 'L', 'L', 'L', 'E', 'D', 'E', 'D', 'A', 'A', 'A', 'A', 'G', 'G', 'G', 'G', 'V', 'V', 'V', 'V',
				'*', 'Y', '*', 'Y', 'S', 'S', 'S', 'S', '*', 'C', 'W', 'C', 'L', 'F', 'L', 'F'};
		complementaria = new char [] {'T', 'G', 'C', 'A'};
		secuencia = formateaYCalculaPorc(fichero);
		diagramaBarras = new DiagramaBarras(pAT, pGC);
		String [] res = calculaInvCom();
		secuenciaInv = res[0];
		secuenciaInvCom = res[1];
		traducciones = new String[6];
		longitudes = new ArrayList [6][4];
		longitudesText = new String [6];
		histogramas = new Histograma[6];
		this.traducir();
	}

	private int StringToIndex(String kmer) {
		int res = 0;
		char [] arrayKmer = kmer.toCharArray();
		for (int i = 0; i < arrayKmer.length; i++) {
			if (arrayKmer[i] == 'A') {
				res = res + 0*(int)Math.pow(4, arrayKmer.length - i -1);
			}
			else if (arrayKmer[i] == 'C') {
				res = res + 1*(int)Math.pow(4, arrayKmer.length - i -1);
			}
			else if (arrayKmer[i] == 'G') {
				res = res + 2*(int)Math.pow(4, arrayKmer.length - i -1);
			}
			else if (arrayKmer[i] == 'T') {
				res = res + 3*(int)Math.pow(4, arrayKmer.length - i -1);
			}
		}
		return res;
	}

	private String formateaYCalculaPorc(String fichero) {
		String res = "";
		try {
			Scanner sc = new Scanner(new File(fichero));
			sc.nextLine();
			int cnt = 0;
			while (sc.hasNextLine()) {
				for (char nucleotido : sc.nextLine().toCharArray()) {
					if (cnt % 50 == 0 && cnt != 0) {
						res = res + "\n";
					} else if (cnt % 10 == 0 && cnt != 0) {
						res = res + " ";
					}
					porcentajesSec[StringToIndex(String.valueOf(nucleotido))] += 1;
					res = res + nucleotido;
					cnt = cnt + 1;
				}
			}
			for (int i = 0; i < 4; i++) {
				porcentajesSec[i] = (porcentajesSec[i]/cnt)*100;
			}
			for (char n : "ACTG".toCharArray()) {
				porcentajesSecInvCom[StringToIndex(String.valueOf(n))] = porcentajesSec[StringToIndex(String.valueOf(complementaria[StringToIndex(String.valueOf(n))]))];
			}
			pAT = (float) porcentajesSec[StringToIndex("A")] + (float) porcentajesSec[StringToIndex("T")];
			pGC = (float) porcentajesSec[StringToIndex("G")] + (float) porcentajesSec[StringToIndex("C")];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private String[] calculaInvCom() {
		String secInvCom = "";
		String secInv = "";
		String molde = secuencia;
		molde = molde.replaceAll(" ", "");
		molde = molde.replaceAll("\n", "");
		int cnt = 0;
		for (int i = molde.length() - 1; i >= 0; i--) {
			if (cnt % 50 == 0 && cnt != 0) {
				secInvCom = secInvCom + "\n";
				secInv = secInv + "\n";
			} else if (cnt % 10 == 0 && cnt != 0) {
				secInvCom = secInvCom + " ";
				secInv = secInv +  " ";
			}
			secInvCom = secInvCom + complementaria[StringToIndex(molde.substring(i, i + 1))];
			secInv = secInv + molde.substring(i, i + 1);
			cnt = cnt +1;
		}
		return new String [] {secInv, secInvCom};
	}
	
	private void traducir() {
		for (int i : Arrays.asList(-3, -2, -1, 1, 2, 3)) {
			traduceEnMarcoIndicado(i);
		}
	}

	private void traduceEnMarcoIndicado(int marcoLectura) {
		String res = "";
		String sec = marcoLectura < 0 ? secuenciaInvCom : secuencia;
		sec = sec.replaceAll(" ", "").replaceAll("\n", "");
		int cnt = 0;
		for (int i = Math.abs(marcoLectura) - 1; i < sec.length() - 2; i = i + 3) {
			if (cnt % 30 == 0 && cnt != 0) {
				res = res + "\n";
			} else if (cnt % 10 == 0 && cnt != 0) {
				res = res + " ";
			}
			String triplete = sec.substring(i, i + 3);
			char aminoacido = aminoacidos[StringToIndex(triplete)];
			res = res + aminoacido;
			cnt = cnt + 1;
		}
		int pos = marcoLectura < 0 ? marcoLectura+3 : marcoLectura+2;
		traducciones[pos] = res;
		cnt = 0;
		String [] fragmentos = res.replace(" ", "").replace("\n", "").split("\\*");
		List <String> frags = new ArrayList <String>();
		List <Integer> posInicial = new ArrayList <Integer>();
		List <Integer> posFinal = new ArrayList <Integer>();
		List <Integer> longitud = new ArrayList <Integer>();
		for (String fragmento : fragmentos) {
			if (fragmento.equals("")) {
				cnt = cnt +1;
			}
			else {
				frags.add(fragmento);
				posInicial.add(cnt);
				posFinal.add(cnt+fragmento.length()-1);
				longitud.add(fragmento.length());
				cnt = cnt+fragmento.length()+1;
			}
		}
		longitudes[pos][0] = frags;
		longitudes[pos][1] = posInicial;
		longitudes[pos][2] = posFinal;
		longitudes[pos][3] = longitud;
		preparaLongitudes(pos);
		histogramas[pos] = new Histograma(getTamaños(pos, longitud), "Histograma ML " + marcoLectura);
	}

	private void preparaLongitudes(int pos) {
		String res = "";
		int numCifrasFragmentos = String.valueOf(longitudes[pos][0].size()).length();
		int numCifrasPosiciones = String.valueOf(traducciones[pos].replace(" ", "").replace("\n", "").length()).length();
		int numCifrasLongitudes = String.valueOf(calculaMaximo(longitudes[pos][3].toArray())).length();
		int cnt = 1;
		for (int i = 0; i < longitudes[pos][0].size(); i++) {
			Formatter f1 = new Formatter();
			Formatter f2 = new Formatter();
			Formatter f3 = new Formatter();
			Formatter f4 = new Formatter();
			String fragmento = (String) longitudes[pos][0].get(i);
			int posInicial = (int) longitudes[pos][1].get(i);
			int posFinal = (int) longitudes[pos][2].get(i);
			int longitud = (int) longitudes[pos][3].get(i);
			res = res + "F" + f1.format("%0" + numCifrasFragmentos + "d", cnt) + ": " + "pos: [" + f2.format("%0" + numCifrasPosiciones + "d", posInicial) + ", " + f3.format("%0" + numCifrasPosiciones + "d", posFinal) + "]  Lon: " + f4.format("%0" + numCifrasLongitudes + "d", longitud) + "  Seq: " + fragmento + "\n";
			cnt = cnt + 1;
			f1.close();
			f2.close();
			f3.close();
			f4.close();
		}
		longitudesText[pos] = res;
	}
	
	public String getPorcentajesToString() {
		String cuerpoPS = getListaPorcentajesSec();
		String cuerpoPSIC = getListaPorcentajesSecInvCom();
		String cuerpoPA = getListaPorcentajesAgrupados();
		return "\n\n Porcentajes: \n\n" + "       - Secuencia: \n" + cuerpoPS + "\n" +
		"       - Secuencia inversa complementaria: \n" + cuerpoPSIC + "\n" +
		"       - Porcentajes agrupados por nucleótidos complementarios: \n" + cuerpoPA + "\n\n";
	}
	
	public String getListaPorcentajesSec() {
		String cuerpoPS = "";
		for (char a : "ACTG".toCharArray()) {
			cuerpoPS =  cuerpoPS + "            -  " + a + ":  " + (float) porcentajesSec[StringToIndex(String.valueOf(a))]+ "% \n";
		}
		return cuerpoPS;
	}
	
	public String getListaPorcentajesSecInvCom() {
		String cuerpoPSIC = "";
		for (char a : "ACTG".toCharArray()) {
			cuerpoPSIC =  cuerpoPSIC + "            -  " + a + ":  "  + (float) porcentajesSecInvCom[StringToIndex(String.valueOf(a))]+ "% \n";
		}
		return cuerpoPSIC;
	}
	
	public String getListaPorcentajesAgrupados() {
		return  "           -  " + "A-T" + ":  " + pAT + "%" + "              -  " + "G-C" + ":  " + pGC + "% \n";
	}
	
	private int calculaMaximo(Object [] numeros) {
		int max = 0;
		for (Object o : numeros) {
			if((int) o > max) {
				max = (int) o;
			}
		}
		return max;
	}

	public double [] getTamaños(int pos, List<Integer> lista) {
		double [] res = new double [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			res[i] = (double) lista.get(i);
		}
		return res;
	}

	public void redactaPDF() {
		Document d = new Document (PageSize.A4, 36, 36, 36, 36);
		String nombreFichero = "Secuencias_y_Traducción" + fichero +".pdf";
		Font fuenteGrande = new Font (Font.FontFamily.COURIER, 20, Font.BOLD);
		Font fuenteMediana = new Font (Font.FontFamily.COURIER, 12, Font.NORMAL);
		Font fuenteMedianaNegrita = new Font (Font.FontFamily.COURIER, 12, Font.BOLD);
		try {
			PdfWriter.getInstance(d, new FileOutputStream(nombreFichero));
			d.open();
			
			Paragraph titulo = escribe("Secuencias y Traducción de " +  fichero + "\n\n", Element.ALIGN_CENTER,
					fuenteGrande);
			d.add(titulo);
			
			Paragraph cabeceraSec = escribe("La secuencia de ADN es: \n\n", Element.ALIGN_LEFT, fuenteMediana);
			d.add(cabeceraSec);
			
			Paragraph secuenciaADN = escribe(secuencia, Element.ALIGN_CENTER, fuenteMediana);
			d.add(secuenciaADN);
			
			Paragraph cabeceraSecInv = escribe("\n\n Secuencia inversa: \n\n", Element.ALIGN_LEFT, fuenteMediana);
			d.add(cabeceraSecInv);
			
			Paragraph secuenciaInvADN = escribe(secuenciaInv, Element.ALIGN_CENTER, fuenteMediana);
			d.add(secuenciaInvADN);
			
			Paragraph cabeceraSecInvCom = escribe("\n\n Secuencia inversa complementaria: \n\n", Element.ALIGN_LEFT, fuenteMediana);
			d.add(cabeceraSecInvCom);
			
			Paragraph secuenciaInvComADN = escribe(secuenciaInvCom, Element.ALIGN_CENTER, fuenteMediana);
			d.add(secuenciaInvComADN);
			
			Paragraph porcentajes = escribe(getPorcentajesToString() + "Diagrama de barras: \n\n", Element.ALIGN_LEFT, fuenteMediana);
			d.add(porcentajes);
			
			BufferedImage imab = diagramaBarras.getChart().createBufferedImage(300, 200);
			Image imb = Image.getInstance(imab, Color.WHITE);
			imb.setAlignment(Element.ALIGN_CENTER);
			d.add(imb);
			
			for (int i : Arrays.asList(3,4,5,2,1,0)) {
				if (i == 3) {
					d.newPage();
					Paragraph traduccion = escribe("\n\n La traducción de esta secuencia se muestra a continuación: ", 
							Element.ALIGN_LEFT, fuenteMediana);
					d.add(traduccion);
				}
				int ml = i < 3 ? i-3 : i-2;
				Paragraph cabeceraM = escribe("\n\n Marco de lectura " + ml + ": ", Element.ALIGN_LEFT, fuenteMedianaNegrita);
				d.add(cabeceraM);
				String secRes = "";
				int cnt = 0;
				for (String s : traducciones[i].replace("\n", " ").split(" ")) {
					if (cnt % 5 == 0 && cnt != 0) {
						secRes = secRes + "\n";
					}
					secRes = secRes + " " + s;
					cnt = cnt + 1;
				}
				Paragraph secuencia = escribe("\n\n Secuencia: \n\n"+ secRes + "\n\n", 
						Element.ALIGN_CENTER, fuenteMediana);
				d.add(secuencia);
				
				Paragraph cabeceraLongitudes = escribe("\n\n Las longitudes exactas de los fragmentos son: \n\n",
						Element.ALIGN_CENTER, fuenteMediana);
				d.add(cabeceraLongitudes);
				
				PdfPTable tabla = new PdfPTable(4);
			    tabla.setWidthPercentage(100);
			    tabla.setWidths(new float[] {10, 12, 10, 35});
				
				tabla.addCell(escribe("Fragmento", Element.ALIGN_CENTER, fuenteMediana));
				tabla.addCell(escribe("Posición", Element.ALIGN_CENTER, fuenteMediana));
				tabla.addCell(escribe("Longitud", Element.ALIGN_CENTER, fuenteMediana));
				tabla.addCell(escribe("Secuencia", Element.ALIGN_CENTER, fuenteMediana));
				
				for (String fila : longitudesText[i].split("\n")) {
					for (String s : fila.replace("pos", "").replace("Lon", "").replace("Seq", "").replace(" ", "").split(":")) {
						if (! s.equals("")) {
							tabla.addCell(escribe(s, Element.ALIGN_CENTER, fuenteMediana));
						}
					}
				}
				d.add(tabla);
			}
			Paragraph histograma = escribe("\n\n Histogramas: \n\n", Element.ALIGN_LEFT, fuenteMediana);
			d.add(histograma);
			for (int i : Arrays.asList(3,4,5,2,1,0)) {
				BufferedImage ima = histogramas[i].getChart().createBufferedImage(300, 200);
				Image im = Image.getInstance(ima, Color.WHITE);
				im.setAlignment(Element.ALIGN_CENTER);
				d.add(im);
				if (i != 0) {
					d.add(new Paragraph ("\n\n"));
				}
			}
			d.close();
			System.out.println("Las secuencias y traducción se han guardado en el fichero " + nombreFichero);
		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}

	private Paragraph escribe(String s, int alineamiento, Font f) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(alineamiento);
		c.append(s);
		c.setFont(f);
		p.add(c);
		return p;
	}
	
}