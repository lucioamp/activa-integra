/*
** Javascript RSS parser
**
** copyright (c) Stas Verberkt (Legolas), 2001 - 2006
** Released under the GNU General Public License
**
** WWW: http://www.legolasweb.nl/
** E-mail: legolas@legolasweb.nl
**
** Using:
** Include this JS file in your page
** Make sure there's a div with id="rss"
** To change the feed edit the feed var at the bottom
** default it uses the query string
** (it prepends the query string with get.php?,
** because it isn't allowed to open extern sites using xmlHTTPRequest.
** get.php is a php file which servers the page.)
*/

/* Functions */
function parseRSS(rss, goal) 
{
	var roots = rss.getElementsByTagName('channel');

	for (var i = 0; i < roots.length; i++) 
	{
		var rssdiv = document.createElement('div');
		goal.appendChild(rssdiv);
		rssdiv.setAttribute('id', 'rss' + i);

		var title = null;
		var desc = null;
		var link = null;
		var items = new Array();
		var botoes = new Array();
		var image = new Array();
		var indItem = 0;
		var indBot = 0;

		for ( var j = 0; j < roots[i].childNodes.length; j++ ) 
		{
			if ( !roots[i].childNodes[j]['tagName'] )
			{
				continue;
			}

			with ( roots[i].childNodes[j] )
			{
				if (tagName == 'title') 
				{
					if ( firstChild )
						title = firstChild.nodeValue;
				}
				else if (tagName == 'description') 
				{
					if ( firstChild )
						desc = firstChild.nodeValue;
				}
				else if (tagName == 'link') 
				{
					if ( firstChild )
						link = firstChild.nodeValue;
				}
				else if (tagName == 'image') 
				{
					if ( firstChild )
						image = parseElement(roots[i].childNodes[j]);
				}
				else if (tagName == 'item') 
				{
					var item;
					
					item = parseElement(roots[i].childNodes[j]);
					if ( !item['pubDate'] && !item['author'] && !item['description'] )
					{
						botoes[indBot] = item;
						indBot++;
					}
					else
					{
						items[indItem] = item;
						indItem++;
					}
				}
			}
		}

		/*
		 * Titulo - rsstitle
		 */
		var eltitle = document.createElement('p');
		eltitle.setAttribute('class', 'rsstitle');
		rssdiv.appendChild(eltitle);
		
		var ellink = document.createElement('a');
		eltitle.appendChild(ellink);
		
		ellink.setAttribute('onclick', 'return abrePaginaSimples("' + link + '", "docDiv");');
		ellink.setAttribute('title', link);
		
		ellinktitle = document.createTextNode(title);
		ellink.appendChild(ellinktitle);

		/*
		 * Descrição - rssdesc
		 */
		var eldesc = document.createElement('p');
		rssdiv.appendChild(eldesc);
		eldesc.setAttribute('class', 'rssdesc');

		var eldesctext = document.createTextNode(desc);
		eldesc.appendChild(eldesctext);
		
		/*
		 * Imagem - rssimage
		 */
		if ( image.length > 0 )
		{
			var elimgdiv = document.createElement('div');
			rssdiv.appendChild(elimgdiv);
			elimgdiv.setAttribute('id', 'rss' + i + '-image');
			elimgdiv.setAttribute('class', 'rssimage');
			
			var elimg = document.createElement('img');
			elimgdiv.appendChild(elimg);
			elimg.setAttribute('src', image['url']);
			elimg.setAttribute('alt', image['title']);
		}

		var elbr = document.createElement('br');
		rssdiv.appendChild(elbr);
		
		/*
		 * Itens - rssitems
		 */
		var elitems = document.createElement('div');
		rssdiv.appendChild(elitems);
		elitems.setAttribute('id', 'rss' + i + '-items');
		elitems.setAttribute('class', 'rssitems');

		for ( var j = 0; j < items.length; j++ ) 
		{
			var elitem = document.createElement('div');
			elitems.appendChild(elitem);
			elitem.setAttribute('id', 'rss' + i + '-item' + j);
			elitem.setAttribute('class', 'rssitem');
			
			var elitemtitle = document.createElement('h2');
			elitem.appendChild(elitemtitle);
			elitemtitle.setAttribute('class', 'rssitemtitle');
			
			var elitemlink = document.createElement('a');
			elitemtitle.appendChild(elitemlink);
			elitemlink.setAttribute('onclick', 'return abrePaginaSimples("' + items[j]['link'] + '", "docDiv");');
			elitemlink.setAttribute('title', items[j]['link']);
			elitemlink.setAttribute('class', 'rssitemlink');
			
			var elitemlinktitle = document.createTextNode(items[j]['title']);
			elitemlink.appendChild(elitemlinktitle);
			
			if ( items[j]['pubDate'] )
			{
				var elitemdate = document.createElement('p');
				elitem.appendChild(elitemdate);
				elitemdate.setAttribute('id', 'rss' + i + '-item' + j + '-date');
				elitemdate.setAttribute('class', 'rssitemdate');
			
				var elitemdatetext = document.createTextNode(items[j]['pubDate']);
				elitemdate.appendChild(elitemdatetext);
			}
			
			if ( items[j]['author'] )
			{
				var elitemautor = document.createElement('p');
				elitem.appendChild(elitemautor);
				elitemautor.setAttribute('id', 'rss' + i + '-item' + j + '-author');
				elitemautor.setAttribute('class', 'rssitemdate');
			
				var elitemautortext = document.createTextNode(items[j]['author']);
				elitemautor.appendChild(elitemautortext);
			}
			
			if ( items[j]['description'] )
			{
				var txtDesc;
				var elitemtexttext;
				
				var elitemtext = document.createElement('div');

				elitemtext.setAttribute('id', 'rss' + i + '-item' + j + '-content');
				elitemtext.setAttribute('class', 'rssitemcontent');
				
				txtDesc = items[j]['description'];
				elitemtext.innerHTML = txtDesc;

				elitem.appendChild(elitemtext);
			}
		}
		
		if ( botoes.length > 0 )
		{
			var elitem = document.createElement('div');
			elitems.appendChild(elitem);
			elitem.setAttribute('id', 'rss' + i + '-item' + j);
			elitem.setAttribute('class', 'rssbot');
			
			var elitemform = document.createElement('form');
			elitem.appendChild(elitemform);

			for ( var j = 0; j < botoes.length; j++ ) 
			{
				
				var elitembotao = document.createElement('input');
				elitemform.appendChild(elitembotao);
				elitembotao.setAttribute('type', 'button');
				elitembotao.setAttribute('value', botoes[j]['title']);
				elitembotao.setAttribute('onclick', 'return abrePaginaSimples("' + botoes[j]['link'] + '", "docDiv");');
			}
		}
	}
}

function parseElement(root) 
{
	var element = new Array();
	for (var i = 0; i < root.childNodes.length; i++) {
		if ( root.childNodes[i].tagName ) 
		{
			// Evita o erro quando esta cara não está definido
			if ( root.childNodes[i].firstChild )
				element[root.childNodes[i].tagName] = root.childNodes[i].firstChild.nodeValue;
		}
	}

	return element;
}

