package util.integra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.integra.Autenticacao;
import modelo.integra.Metodo;
import modelo.integra.NoArvoreResposta;
import modelo.integra.Parametro;
import modelo.integra.Recurso;
import modelo.integra.Resposta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WADLParser {
	Document dom;

	int idAplicacao;

	List<Metodo> metodos;
	List<Recurso> recursos;
	List<Resposta> respostas;
	List<Autenticacao> autenticacoes;

	List<NoArvoreResposta> nosArvResp;

	public WADLParser() {
		// create a list to hold the method objects
		nosArvResp = new ArrayList<NoArvoreResposta>();

		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
		respostas = new ArrayList<Resposta>();
		autenticacoes = new ArrayList<Autenticacao>();
	}

	public void parseWADL2BD(int id, String url) throws ExcecaoParser {
		idAplicacao = id;

		// Faz o parsing do arquivo xml criando um objeto DOM
		parseXmlFile(url);

		// Interpreta o WADL a partir do DOM
		parseDocument();

		// Grava o WADL interpretado no banco
		// saveData();

		// Para depuração
		printData();
	}

	public List<Recurso> parseWADL(String url) throws ExcecaoParser {
		// Faz o parsing do arquivo xml criando um objeto DOM
		parseXmlFile(url);

		// Interpreta o WADL a partir do DOM
		parseDocument();

		return recursos;
	}

	private void parseXmlFile(String url) throws ExcecaoParser {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse(url);
		} catch (ParserConfigurationException pce) {
			throw new ExcecaoParser("Erro Parser (" + url + "): " + pce.getMessage());
		} catch (SAXException se) {
			throw new ExcecaoParser("Erro SAX (" + url + "): " + se.getMessage());
		} catch (IOException ioe) {
			throw new ExcecaoParser("Erro IO (" + url + "): " + ioe.getMessage());
		}
	}

	private void parseDocument() {
		// Obtem o objeto raiz
		Element docEle = dom.getDocumentElement();

		NodeList nodes = docEle.getChildNodes();

		if (nodes != null && nodes.getLength() > 0) {
			Node no;
			Element elem;

			for (int i = 0; i < nodes.getLength(); i++) {
				no = nodes.item(i);

				if (no.getNodeName().equals("representation") || no.getNodeName().equals("fault")) {
					Resposta rep = getResponse((Element) no);

					if (rep != null)
						respostas.add(rep);
				}
			}
			for (int i = 0; i < nodes.getLength(); i++) {
				no = nodes.item(i);

				if (no.getNodeName().equals("method")) {
					Metodo met = getMethod((Element) no);

					if (met != null)
						metodos.add(met);
				}
			}
			for (int i = 0; i < nodes.getLength(); i++) {
				no = nodes.item(i);

				if (no.getNodeName().equals("resources")) {
					String base;

					elem = (Element) no;

					base = elem.getAttribute("base");

					percorreRecursos(base, "", elem, recursos, null);
				}
			}

		}
	}

	public void percorreRecursos(String base, String pathBase, Node er, List<Recurso> lisRec, Autenticacao autPai) {
		Autenticacao autFilho;
		NodeList rl = er.getChildNodes();

		autFilho = autPai;

		if (rl != null && rl.getLength() > 0) {
			Node res;
			Recurso rec;
			Element elem;
			String path;
			String nome;

			for (int r = 0; r < rl.getLength(); r++) {
				res = rl.item(r);

				if (res.getNodeName().equals("resource")) {
					elem = (Element) res;

					nome = elem.getAttribute("path");
					path = ((pathBase.length() > 0) ? pathBase + "/" : "") + nome;

					rec = new Recurso(idAplicacao, nome, base, path);
					lisRec.add(rec);

					percorreMetodos(res, rec.getMetodos(), autFilho);
					percorreRecursos(base, path, res, rec.getRecursos(), autFilho);
				}
			}
		}
	}

	public void percorreMetodos(Node er, List<Metodo> lisMet, Autenticacao autPai) {
		Node res;
		Metodo met;
		String href;
		String name;
		NodeList rl;
		Element elem;
		Autenticacao autFilho;

		autFilho = autPai;

		rl = er.getChildNodes();

		if (rl != null && rl.getLength() > 0) {
			for (int r = 0; r < rl.getLength(); r++) {
				res = rl.item(r);

				if (res.getNodeName().equals("method")) {
					elem = (Element) res;
					href = elem.getAttribute("href");

					if (href.length() == 0) {
						name = elem.getAttribute("name");

						met = new Metodo(idAplicacao, name, "");
						lisMet.add(met);

						autFilho = percorreParametros(elem, met.getParametros(), autFilho, "request");

						met.setAutenticacao(autFilho);

						percorreResposta(elem, met, "representation");
						percorreResposta(elem, met, "fault");
					} else {
						href = href.substring(1);

						Iterator<Metodo> it = metodos.iterator();
						while (it.hasNext()) {
							met = (Metodo) it.next();
							if (met.getId().equals(href)) {
								met.setAutenticacao(autFilho);

								lisMet.add(met);
							}
						}
					}
				}
			}
		}

	}

	private Autenticacao percorreParametros(Element el, List<Parametro> lisPar, Autenticacao aut, String paiValido) {
		Node pai;
		NodeList pl = el.getElementsByTagName("param");

		if (pl != null && pl.getLength() > 0) {
			String name;
			String required;
			String authMode;
			Parametro par;

			for (int p = 0; p < pl.getLength(); p++) {
				Element pel = (Element) pl.item(p);

				pai = pel.getParentNode();
				if (pai == null || !pai.getNodeName().equals(paiValido))
					continue;

				name = pel.getAttribute("name");
				required = pel.getAttribute("required");
				authMode = pel.getAttribute("authmode");

				if ((name != null) && (name.length() != 0)) {
					if ((authMode != null) && (authMode.length() != 0)) {
						aut = new Autenticacao(idAplicacao, name, pel.getAttribute("style"), pel.getAttribute("type"),
								authMode);
						autenticacoes.add(aut);
					} else {
						par = new Parametro(idAplicacao, name, pel.getAttribute("style"), pel.getAttribute("type"), pel
								.getAttribute("path"), required != null && required.equals("true"));

						NodeList plDoc = pel.getElementsByTagName("doc");
						for (int i = 0; i < plDoc.getLength();) {
							Element pelDoc = (Element) plDoc.item(i);

							String title = pelDoc.getAttribute("title");
							par.setTitle(title);
							break;
						}

						lisPar.add(par);
					}
				}
			}
		}

		return aut;
	}

	private NoArvoreResposta percorreParametrosResposta(Element el, String elemRaizStr) {
		NoArvoreResposta elem, raiz;

		if (elemRaizStr.length() == 0)
			return null;

		raiz = new NoArvoreResposta(idAplicacao, elemRaizStr, elemRaizStr, "", "", false, elemRaizStr);

		NodeList pl = el.getElementsByTagName("param");

		if (pl != null && pl.getLength() > 0) {
			String name;
			String style;
			String type;
			String path;
			String pathVet[];
			NoArvoreResposta propAtual, elemAtual;

			for (int p = 0; p < pl.getLength(); p++) {
				Element pel = (Element) pl.item(p);

				name = pel.getAttribute("name");
				style = pel.getAttribute("style");
				type = pel.getAttribute("type");
				path = pel.getAttribute("path");

				if ((name != null) && (name.length() != 0)) {
					int inicio = 0;

					pathVet = path.split("/");

					propAtual = null;
					elemAtual = raiz;

					// Se começa por barra ignora a tag inicial
					if (path.charAt(0) == '/')
						inicio = 2;

					if (pathVet[0].equals(".")) {
						raiz.setNomeParam(name);
						raiz.setStyle(style);
						raiz.setType(type);
					} else {
						String nomeNo;

						for (int e = inicio; e < pathVet.length; e++) {
							nomeNo = pathVet[e];

							if (nomeNo.charAt(0) == '@') {
								nomeNo = nomeNo.substring(1);
								propAtual = new NoArvoreResposta(idAplicacao, nomeNo, name, style, type, true, path);
								elemAtual.addNoArvore(propAtual);
								break;
							}

							elem = elemAtual.getElemento(nomeNo);
							if (elem == null) {
								elem = new NoArvoreResposta(idAplicacao, nomeNo, name, style, type, false, path);
								elemAtual.addNoArvore(elem);
							}
							elemAtual = elem;
						}
					}

					NodeList plLink = pel.getElementsByTagName("link");
					if (plLink != null && plLink.getLength() > 0) {
						String href;
						Element pelLink = (Element) plLink.item(0);

						href = pelLink.getAttribute("href");

						if ((href != null) && (href.length() != 0)) {
							if (propAtual != null) {
								propAtual.setHref(href);
								nosArvResp.add(propAtual);
							} else {
								elemAtual.setHref(href);
								nosArvResp.add(elemAtual);
							}
						}
					}
				}
			}
		}

		return raiz;
	}

	private void percorreResposta(Element el, Metodo met, String tagName) {
		boolean erro;
		NodeList pl = el.getElementsByTagName(tagName);

		erro = tagName.equals("fault");
		if (pl != null && pl.getLength() > 0) {
			int status;
			Resposta rep;
			String href;
			String statusStr;
			String elemRaizStr;

			for (int p = 0; p < pl.getLength(); p++) {
				Element pel = (Element) pl.item(p);

				href = pel.getAttribute("href");

				if (href.length() == 0) {
					statusStr = pel.getAttribute("status");
					elemRaizStr = pel.getAttribute("element");

					status = 0;
					if (statusStr.length() != 0) {
						try {
							status = Integer.parseInt(statusStr);
						} catch (Exception e) {
							System.out.println("Tipo incorreto de status: '" + statusStr + "'");
							return;
						}
					}

					rep = new Resposta(idAplicacao, pel.getAttribute("id"), pel.getAttribute("mediaType"), elemRaizStr,
							status, erro);

					rep.setEstruturaXML(percorreParametrosResposta(pel, elemRaizStr));

					met.addResposta(rep);
				} else {
					href = href.substring(1);

					Iterator<Resposta> it = respostas.iterator();
					while (it.hasNext()) {
						rep = (Resposta) it.next();
						if (rep.getId().equals(href)) {
							met.addResposta(rep);
							break;
						}
					}
				}

			}
		}
	}

	/**
	 * I take a method element and read the values in, create an Metodo object
	 * and return it
	 * 
	 * @param em
	 * @return
	 */
	private Metodo getMethod(Element elem) {
		String id;
		String name;
		Metodo met;
		Autenticacao aut = null;

		id = elem.getAttribute("id");

		if (id.length() == 0)
			return null;

		name = elem.getAttribute("name");

		met = new Metodo(idAplicacao, name, id);

		NodeList pl = elem.getElementsByTagName("doc");
		for (int p = 0; p < pl.getLength();) {
			Element pel = (Element) pl.item(p);

			String title = pel.getAttribute("title");
			met.setTitle(title);
			break;
		}

		aut = percorreParametros(elem, met.getParametros(), aut, "request");
		percorreResposta(elem, met, "representation");
		percorreResposta(elem, met, "fault");

		return met;
	}

	public Resposta getResponse(Element elem) {
		int status;
		boolean erro;
		Resposta rep;

		String id;
		String statusStr;
		String elemRaizStr;

		erro = elem.getTagName().equals("fault");

		id = elem.getAttribute("id");
		statusStr = elem.getAttribute("status");
		elemRaizStr = elem.getAttribute("element");

		status = 0;
		if (statusStr.length() != 0) {
			try {
				status = Integer.parseInt(statusStr);
			} catch (Exception e) {
				System.out.println("Tipo incorreto de status: '" + statusStr + "'");
				return null;
			}
		}

		if (id.length() == 0)
			return null;

		rep = new Resposta(idAplicacao, id, elem.getAttribute("mediaType"), elemRaizStr, status, erro);

		rep.setEstruturaXML(percorreParametrosResposta(elem, elemRaizStr));

		return rep;
	}

	public void printData() {
		// int n;
		// Iterator it;

		// System.out.println("-------------------------------------------------");
		// System.out.println("No de Autenticacoes '" + autenticacoes.size() +
		// "'.");
		//		
		// n = 0;
		// it = autenticacoes.iterator();
		// while(it.hasNext())
		// {
		// Autenticacao aut = (Autenticacao)it.next();
		//			
		// // aut.setIdBanco(++n);
		// // aut.imprime();
		// }

		// System.out.println("-------------------------------------------------");
		// System.out.println("No de Recursos '" + recursos.size() + "'.");

		Iterator<Recurso> it = recursos.iterator();
		while (it.hasNext()) {
			Recurso rec = (Recurso) it.next();

			rec.imprime(1);
		}
	}

	public void saveData() {
		Iterator<Autenticacao> itAuth = autenticacoes.iterator();
		while (itAuth.hasNext()) {
//			Autenticacao aut = (Autenticacao) it.next();
//
//			aut.incluiDB();
		}

		Iterator<Recurso> itRecurso = recursos.iterator();
		while (itRecurso.hasNext()) {
//			Recurso rec = (Recurso) it.next();
//
//			 rec.incluiDB(-1);
		}

		Iterator<NoArvoreResposta> itLink = nosArvResp.iterator();
		while (itLink.hasNext()) {
			String href;
			NoArvoreResposta no = (NoArvoreResposta) itLink.next();

			href = no.getHref();
			href = href.substring(1);

			Iterator<Metodo> itMetodo = metodos.iterator();
			while (itMetodo.hasNext()) {
				Metodo met = (Metodo) itMetodo.next();

				if (met.getId().equals(href)) {
					// no.setIdLinkMetodo(met.getIdBanco());
					// NoArvoreRespostaPersist.setIdLinkMetodo(no);
					break;
				}
			}
		}
	}

	// Para depuração
	public static void main(String[] args) {
		WADLParser dp;

		// Cria a instância
		dp = new WADLParser();

		try {
			// Faz o parsing do arquivo WADL
			// dp.parseWADL2BD( 1,
			// "http://localhost:8080/activa/wadl/delicious.wadl" );
			dp.parseWADL2BD(1, "http://localhost:8080/activa/wadl/YahooSearch.wadl");
		} catch (ExcecaoParser e) {
			System.out.println("Parser: " + e.getMessage());
		}
	}
}
