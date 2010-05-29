/*
	// Funções criadas por Renato Machado dos Santos
*/
	var isInternetExplorer = (window.ActiveXObject != null);
	
	var DIALOG_CONFIRM = ['Sim', 'Não'];
	
	var modulo_ready = function(arg1, arg2, arg3) {};

	jQuery.fn.extend({
		getPage: function(page)
		{
			return $(this).find("#page_"+page);
		},
		getObject: function() {		
			var serviceId = $(this, this.parentNode).parent('div').parent('div').parent('div').attr('id');
			if(serviceId == null)
			{
				return $_SESSION[($(this).parent('div').attr('class').indexOf("conteudo") > -1 ? $(this).parent('div').attr('id') : $(this).attr('id'))];
			}
			
			var tabId = $(this, this.parentNode).attr('id');
			
			if(tabId == null)
				return null;
						
			return $_SESSION[serviceId][tabId];
		},
		showDialog: function(object)
		{
			object = (object == null ? {} : object);
			var id = rand(1, 9999);
			var linksWork = true;
			var param = {
				modulo_loaded: false,
				maximize: (object.maximize == null ? true : object.maximize),
				minimize: (object.minimize == null ? true : object.minimize),
				resizable: (object.resizable == null ? (isInternetExplorer ? false : false) : object.resizable),
				draggable: (object.draggable == null ? true : object.draggable),
				title: object.title,
				modal: (object.modal == null ? false : object.modal),
				dialogClass: 'janela',
				closeOnEscape: (object.showCloseButton == false ? false : (object.closeOnEscape == null ? true : false)),
				show: (object.show == null ? 'highlight' : object.show),
				hide: (object.hide == null ? null : object.hide),
				width: ((object.width == null/* || !isInternetExplorer*/) ? 'auto' : object.width),
				height: ((object.height == null) ? 'auto' : object.height),
				zIndex: (object.zIndex == null ? (object.modal == true ? 13000 : 1000) : object.zIndex),
				reload: (object.reload == null ? true : object.reload),
				position: (object.position == null ? [rand(1, $(window).height()-200), rand(1, $(window).height())-300] : object.position),
				maxHeight: (object.maxHeight == null ? false : object.maxHeight),
				minHeight: 0,
				open: function() {
					if(object.executeOpen != null)
					{
						object.executeOpen.reference.bind(
							(object.executeOpen.reference.attr('class') == 'icon' ? 'dblclick' : 'click'),
							function(e) { e.preventDefault(); tdCenter.mousedown().mouseup(); }
						);
					}
				
					if ($.isFunction(object.open))
						object.open.apply();
								
					var id = $(this, this.parentNode).attr('id');
					$_SESSION[id] = new Array();
					$_SESSION[id]['focus'] = function() {
						mainDialog.removeClass('minimized').show();
						main.dialog('moveToTop');
					};
						
					if(param.modulo_loaded == false)
					{
						var $_this = $(this).find('div:first');
						if($_this.attr('id') == null || $_this.attr('id').indexOf('page_') == -1)
							$_this = $(this);

						modulo_ready($_this);
						param.modulo_loaded = true;
					}
					
				},
				close: function() {
					if(object.executeOpen != null)
					{
						object.executeOpen.reference.bind(
							(object.executeOpen.reference.attr('class') == 'icon' ? 'dblclick' : 'click'),
							function(e) { e.preventDefault(); object.executeOpen.param.pageToReload = null; $(this).openWindow(object.executeOpen.id, object.executeOpen.param); }
						);
					}
					
					if(object.modal == true)
						$('#menuBar').css('z-index', 13000);
						
					if(minimizedWindows != null)
						minimizedWindows.find('td#mini'+id).remove();
										
					delete $_SESSION[$(this).attr('id')];
					
					if ($.isFunction(object.close))
						object.close.apply();
				},
				beforeclose: function() {
				},
				dragStart: function(event, ui) {
					$(this).hide().parents('div').css('cursor', 'move').addClass('dragging');
									
					//$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').hide();

					if ($.isFunction(object.dragStart))
						object.dragStart.apply();
				},
				dragStop: function(event, ui) {
					$(this).show().parents('div').css('cursor', 'default').css('height', 'auto')/* Correção */.removeClass('dragging');
					
					//$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').show();
											
					if ($.isFunction(object.dragStop))
						object.dragStop.apply();
					
					linksWork = false;
					setTimeout(function() { linksWork = true }, 500);
				},
				/*drag: function(event, ui) {},*/
				posLeft : 0,
				posTop : 0,
				focus: function(event, ui) {
					setFocus(id, $(this).parents(".ui-dialog"));
					hideMenuOption();
					
					if($(this).beFullSize())
						$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').hide(); /* Performance */
					
					if ($.isFunction(object.focus))
						object.focus.apply();
				}
			};
			
			var minimizedWindows = $('#menuBar table#minimizedWindows tr').disableSelection();
			var setFocus = function(id, mainDialog, focus)
			{
				var id = '#mini'+id;				
				minimizedWindows.find('[class$="-focused"][id!="'+id+'"]').each(function(i, o) {
					var $this = $(o); $this.attr('class', $this.attr('class').replace('-focused', ''));
				});
				
				if(focus != false)
				{
					$('.ui-dialog').changeClass('ui-widget-content', 'ui-widget-content-disabled').find('.conteudo').css('opacity', 0.43)
					.parents('div:second').find('.ui-dialog-titlebar').changeClass('ui-widget-header', 'ui-widget-header-disabled');
					
					mainDialog.changeClass('ui-widget-content-disabled', 'ui-widget-content').find('.conteudo').css('opacity', 1.0)
					.parents('div:second').find('.ui-dialog-titlebar').changeClass('ui-widget-header-disabled', 'ui-widget-header');
				
					minimizedWindows.find('#'+id+':not([class$="-focused"])').each(function(i, o) {
						var $this = $(o); $this.attr('class', $this.attr('class')+'-focused');
					});
				}else
					mainDialog.addClass('minimized');
			};

			var main = $(this).dialog(param).css('height', param.height)/* Correção para IE */;
			var mainDialog = $(this).parents(".ui-dialog");
			var buttons = {};
			
			if(param.maximize == true)
			{
				mainDialog.removeClass('ui-draggable').find('.ui-dialog-titlebar').dblclick(function(e){
					if(main.beFullSize())
						minimize(main, mainDialog, param.width, param.height);
					else
						maximize(main, mainDialog);
				});			
			
				var linkMaximize = $('<a></a>').attr('href', '#').attr('class', 'ui-dialog-titlebar-extlink ui-corner-all')
				.append('<span class="ui-icon ui-icon ui-icon-extlink">maximize</span>')
				.click(function(e){
					e.preventDefault();
					
					if(linksWork == false)
						return true;
					
					if(param.minimize == true && main.beFullSize())
						minimize(main, mainDialog, param.width, param.height);
					else if(param.maximize == true)
						maximize(main, mainDialog);
				});	
			
				mainDialog.find('.ui-dialog-titlebar .ui-dialog-titlebar-close').before(linkMaximize);
			}
			
			var maximize = function(main, mainDialog)
			{
				$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').hide(); /* Performance */
				mainDialog.find('.ui-icon-extlink').changeClass('ui-icon-extlink', 'ui-icon-newwin');														
				main.dialog('option', 'posLeft', mainDialog.css('left')).dialog('option', 'posTop', mainDialog.css('top'))
					.dialog('option', 'draggable', false).dialog('option', 'width', '100%').dialog('option', 'height', '97%')
					.dialog('option', 'position', 'top').css('height', '97%');
				
				/* Correção para tela de loading para ajax */
				mainDialog.find('div#sending').each(function() {
					var page = null;

					if($(this).parent('div').attr('id') == '')
						full = true;
					else
						page = $(this).parent('div');
					
					$(this).css('width', (full ? '100%' : page.css('width'))).css('height', (full ? '100%' : page.css('height')));
				});
				
			};
			
			if(param.minimize == true)
			{
				var tdCenter = $('<td></td>').attr('id', 'mini'+id).attr('class', 'taskbutton-center-focused');
				
				var fistPosition;
				var widthDialog;
				var heightDialog;
				
				var linkMinimizar = $('<a></a>').attr('href', '#').attr('class', 'ui-dialog-titlebar-minusthick ui-corner-all')
				.append('<span class="ui-icon ui-icon ui-icon-minusthick">minimize</span>')
				.click(function(e){
					e.preventDefault();
					
					if(linksWork == false)
						return true;
					
					fistPosition = {left: mainDialog.offset().left, top: mainDialog.offset().top};
					widthDialog = mainDialog.css('width');
					heightDialog = mainDialog.css('height');
					
					$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').show(); /* Performance */
					mainDialog.animate(
						{
							"left": tdCenter.offset().left+30,
							"top": tdCenter.offset().top-20,
							"width": "-=700",
							"height": "-=400"
						}
					, 200, function() {
						setFocus(id, mainDialog.hide(), false);
					});
				});
				
				buttons.Minimizar = {
					onClick: function() {
						linkMinimizar.click();
					}
				};
				
				mainDialog.find('.ui-dialog-titlebar .ui-dialog-title').after(linkMinimizar);
				
				minimizedWindows
					.append($('<td></td>').attr('id', 'mini'+id).attr('class', 'taskbutton-left-focused').append('&nbsp;'))
					.append(tdCenter.append('<a href="#">&nbsp;&nbsp;'+param.title+'&nbsp;&nbsp;</a>')
						.bind('mousedown', function(e)
							{
								e.preventDefault();
								if($(this).attr('class') == 'taskbutton-center-focused')
									$(this).one('mouseup', function(e) {
										setFocus(id, mainDialog, false);
										fistPosition = {left: mainDialog.offset().left, top: mainDialog.offset().top};
										widthDialog = mainDialog.css('width');
										heightDialog = mainDialog.css('height');
										mainDialog.animate(
											{
												"left": tdCenter.offset().left+30,
												"top": tdCenter.offset().top-20,
												"width": "-=700",
												"height": "-=400"
											}
										, 200, function() {
											setFocus(id, mainDialog.hide(), false);
										});
									});
								else
								{
									$(this).one('mouseup', function(e) {
										$(this).unbind('mouseout');
										if(!mainDialog.is(':visible'))
										{
											mainDialog.show().animate(
												{
													"left": fistPosition.left,
													"top": fistPosition.top,
													"width": "+=700",
													"height": "+=400"
												}
											, 200, function() {
												mainDialog.removeClass('minimized');
											});
										}
									}).one('mouseout', function() { $(this).unbind('mouseup'); setFocus(id, mainDialog, false); });
									main.dialog('moveToTop');
								}
								
								/*								if($(this).attr('class') == 'taskbutton-center-focused')
									$(this).one('mouseup', function(e) { setFocus(id, mainDialog, false); mainDialog.hide(); });
								else
								{
									$(this).one('mouseup', function(e) { $(this).unbind('mouseout'); mainDialog.removeClass('minimized').show(); }).one('mouseout', function() { setFocus(id, mainDialog, false); });
									main.dialog('moveToTop');
								}
								 */
							}
						)/*.contextMenu({
							buttons: {
								Fechar: {
									onClick: function() {
										main.dialog('close').dialog('destroy').remove();
									}
								}
							}}
					)*/).click(function(e) { e.preventDefault(); })
					.append($('<td></td>').attr('id', 'mini'+id).attr('class', 'taskbutton-right-focused').append('&nbsp;'));
			}
			
			var minimize = function(main, mainDialog, width, height)
			{
				$('.ui-dialog.ui-widget-content-disabled:not([class*="minimized"])').show(); /* Performance */
				main.dialog('option', 'draggable', true).dialog('option', 'width', width).dialog('option', 'height', height)./*dialog('option', 'position', 'center').*/css('height', height);
				mainDialog.removeClass('ui-draggable').css('height', 'auto')
				.css('left', main.dialog('option', 'posLeft')).css('top', main.dialog('option', 'posTop'))
				.find('.ui-icon-newwin').changeClass('ui-icon-newwin', 'ui-icon-extlink');
				
				/* Correção para tela de loading para ajax */
				mainDialog.find('div#sending').each(function() {
					var page = null;

					if($(this).parent('div').attr('id') == '')
						full = true;
					else
						page = $(this).parent('div');
					
					$(this).css('width', (full ? '100%' : page.css('width'))).css('height', (full ? '100%' : page.css('height')));
				});
			};
			
			if(param.reload == true)
			{
				var janelaId = object.executeOpen.id;
				
				var linkRefresh = $('<a></a>').attr('href', '#').attr('class', 'ui-dialog-titlebar-refresh ui-corner-all')
				.append('<span class="ui-icon ui-icon ui-icon-refresh">reload</span>')
				.click(function(e){
					e.preventDefault();
					
					if(linksWork == false)
						return true;
					
					var reload = function() {
						var tab = mainDialog.find('#tabs');
						if(tab.html() != null)
						{
							var tabSelected = main.getObject()['tabSelected'];
							
							if($_SESSION[janelaId][tabSelected['id']]['onReload'] != null)
								$_SESSION[janelaId][tabSelected['id']]['onReload']();
							else
								tab.tabs('load', tabSelected['index']);
						}else
						{
							object.pageToReload = mainDialog.find('div.conteudo');
							object.pageToReload.empty();
							object.moduloCallback = function() {
								if($_SESSION[janelaId]['onReload'] != null)
									$_SESSION[janelaId]['onReload']();
								else
									modulo_ready(main);
							};
							object.executeOpen.reference.openWindow(object.executeOpen.id, object);
						}
					};
					
					if($_SESSION[janelaId]['reloadNoQuestion'] == true)
					{
						reload();
						$_SESSION[janelaId]['reloadNoQuestion'] = false;
					}else					
						mainDialog.alert('Tem certeza que deseja recarregar esta pagina?', null, {dialog: ['Sim', 'Não'], clickOK: reload });
				});
				
				buttons.Recarregar = {
					onClick: function() {
						linkRefresh.click();
					}
				};
				
				$_SESSION[janelaId]['buttonReload'] = linkRefresh;
				
				mainDialog.find('.ui-dialog-titlebar .ui-dialog-title').after(linkRefresh);
			}
			
			
			if(object.showCloseButton == false)
				mainDialog.find(".ui-dialog-titlebar-close").remove();
			else
			{
				mainDialog.find('.ui-dialog-titlebar-close').unbind().bind('click', function(e) {
					e.preventDefault();
					
					if(linksWork == false)
						return true;
					main.dialog('close').dialog('destroy').remove();
				});
				
				buttons.Fechar = {
					textBold: true,
					onClick: function() {
						main.dialog('close').dialog('destroy').remove();
					}
				};
				
				mainDialog.find('.ui-dialog-titlebar').contextMenu({
					buttons: buttons
				});
			}
				
			return $(this);
		},
		TabsGenerate: function(option, callback)
		{
			var $this = $(this);
			option = (option == null ? {} : option);
			
			$this.hide();
			
			setTimeout(function() {
				$this.tabs({cache: true, /*fx: { opacity: 'toggle', duration: 200 },*/ spinner: 'Carregando...',
					load: function(event, ui) {
						var $thisTab = $(this);
						setTimeout(function() {
							var object = $(ui.panel);							
							var page = $('<div id="page_1"></div>').html(object.html());
							object.html(page);
							
							var serviceId = object.parent('div').parent('div').parent('div').attr('id');
							var tabId = object.attr('id');
							
							$_SESSION[serviceId][tabId] = new Array();
							$_SESSION[serviceId][tabId]['id'] = ui.index;
							
							modulo_ready(object, $this);
							
							if(object.getObject()['onLoad'] != null)
								object.getObject()['onLoad'](event, ui);
						}, 200);
					}, show: function(event, ui) {
						var $thisTab = $(this);					
						setTimeout(function() {
							var object = $(ui.panel).getObject();
							
							$this.parents().parents().getObject()['tabSelected'] = {index: ui.index, id: $(ui.panel).attr('id')};
							
							if(object['onShow'] != null)
								object['onShow'](event, ui);
						}, 200);
					}, select: function(event, ui) {
						var $thisTab = $(this);					
						setTimeout(function() {
							var object = $(ui.panel).getObject();
							
							if(object != null)
							{
								if(object['cache'] == false)
									$this.tabs('load', ui.index);
								
								if(object['onSelect'] != null)
									object['onSelect'](event, ui);
							}
						}, 200);
					}			
				}).find(".ui-tabs-nav").sortable({axis:'x'});
				
				$this.fadeIn();
				
				if ($.isFunction(callback))
					callback.apply();
			}, 200);
			
			return $(this);
		},
		beFullSize: function()
		{
			return ($(this).dialog('option', 'width') == '100%' && $(this).dialog('option', 'height') == '97%');
		},
		clear: function(callback)
		{
			return $(this).hide('highlight', function() {
				$(this).remove();
				
				if (callback != null && $.isFunction(callback))
					callback.call(this);
			});
		},
		addTab: function(idTab, file, title)
		{
			var $this = $(this);
			var abas = $this.find('ul.ui-tabs-nav li');
			var id = abas.length-1;
			
			abas.each(function(i) {
				if($(this).attr('id') == idTab)
				{
					$this.tabs('select', i);
					return false;
				}else if(id == i)
				{
					$this.tabs('add', file, title, abas.length);
					abas = $this.find('ul.ui-tabs-nav li');
					id = abas.length-1;
					$this.tabs('select', id);
					abas.eq(id).attr('id', idTab).append(
						$('<a href="#"></a>').attr('style', 'cursor: pointer; font-weight: bold; margin: -1px 0px; padding: 0px 1px;')
						.append('[x]').click(function(e) {
							e.preventDefault();
							$this.removeTab(idTab);
						})
					);
				}
			});
			
			return $this;
		},
		removeTab: function(id, todos)
		{
			var $this = $(this);
			
			var pesquisar = function()
			{
				$this.find('ul.ui-tabs-nav').find('li').each(function(_pos) {
					if(todos == true && $(this).attr('id').indexOf(id+"_", 0) > -1 || $(this).attr('id') == id)
					{
						$this.tabs('remove', _pos);
						if(todos == true)
							pesquisar();
						
						return false;
					}
				});	
			}
			pesquisar();
		},
		changeClass: function(oldClass, newClass)
		{
			return $(this).removeClass(oldClass).addClass(newClass);
		},
		createOptionTreeTable: function(param, onDrag)
		{
			var tbody = $(this).find('tbody');			
			
			var tr = $('<tr></tr>').attr('id', 'node-'+param.id+(param.isFile == true ? '_' : '')).mousedown(function() {
				$("tr.selected").removeClass("selected"); // Deselect currently selected rows
				$(this).addClass("selected");
			});

			var span = $('<span></span>').addClass(param.isFile == true ? 'file' : 'folder').html(param.nome)
			if(param.raiz != true)
			{
				span.mousedown(function() {
					$($(this).parents("tr")[0]).trigger("mousedown");
				}).draggable({
					helper: "clone",
					opacity: .75,
					refreshPositions: true,
					revert: "invalid",
					revertDuration: 300,
					scroll: true
				});
			}
			
			var tdDetalhe = $('<td></td>').html(span);
			tr.append(tdDetalhe);

			var inTbody = true;
			if(param.append != true && param.raiz != true && param.parent_id >= 0)
			{
				tr.addClass('child-of-node-'+param.parent_id);
				_tr = tbody.find('tr[id="node-'+param.parent_id+'"]');

				if(_tr.html() != null)
				{
					inTbody = false;
					_tr.after(tr);
				}
			}
			
			if(inTbody == true)
				tbody.append(tr);
			
			if(param.isFile != true)
			{
				span.each(function() {
					$($(this).parents("tr")[0]).droppable({
						accept: ".folder, .file",
						drop: function(e, ui) { 
							if($($(ui.draggable).parents("tr")[0]).appendBranchTo(this) == true)
							{
								// Issue a POST call to send the new location (this) of the 
								// node (ui.draggable) to the server.
								var _id = $(ui.draggable).parents("tr")[0].id.split("-")[1].split("_")[0];
								var parent = this.id.split("-")[1];
								
								onDrag.call(this, _id, $(ui.draggable).html().trim(), parent, $(ui.draggable).parents("tr")[0].id.indexOf("_") != -1);
							}
						},
						hoverClass: "accept",
						over: function(e, ui) {
							if(this.id != $(ui.draggable.parents("tr.parent")[0]).id && !$(this).is(".expanded")) {
								$(this).expand();
							}
						}
					});
				});
			}
			
			return {
				getTr: function() { return tr; },
				getTd: function() { return tdDetalhe; },
				getSpan: function() { return span; }
			};
		},
		_treeTable: function(nome, context)
		{
			$this = $(this);
			
			if(nome == null)
			{
				$this.find('tbody tr:first').removeClass('initialized');
				$this.treeTable();
				var span = $this.find('span.expander:first').click();
				setTimeout(function() { span.click(); }, 0);
				return $this;
			}
			
			var option = $this.createOptionTreeTable({id: 0, nome: nome, isFile: false, raiz: true}, function(id, name, parent, isFile) {
				if(isFile == false)
					$this.sendRequest("../../MembroFavoritoServlet?opcao=P", {pkPasta: id, pkParent: parent, tipo: "alterar"});
				else
					$this.sendRequest("../../MembroFavoritoServlet?opcao=F", {pkFavorito: id, pkPasta: parent, tipo: "alterarPasta"});
			});

			var tr = option.getTr().prependTo($this);
			var tdDetalhe = option.getTd()/*.attr('style', 'margin-left: -5px; padding-left: 5px;')*/;
			var span = option.getSpan().attr('class', 'list');
			tr.append($('<td></td>').html('&nbsp;'));
			
			$this.treeTable();
			tdDetalhe.find('span:first').click()/*.hide()*/;
			
			if(context != null)
				tdDetalhe.contextMenu(context);
			
			tr.removeClass('initialized');
			
			return $this;
		},
		alert: function(msg, titulo, option)
		{
			$('#menuBar').css('z-index', '18000');
			if($('div#messageBox_Alert').html() == null)
				$('body').append('<div id="messageBox_Alert" title="" style="display: none;"><div id="conteudo"></div><p style="text-align: center;"></p></div>');
				
			var $this = $(this);
			var alertForm = $('div#messageBox_Alert');
			
			var alertHeader = alertForm.parent().find('.ui-widget-header');
			var isModal = $this.dialog('option', 'modal');
			option = (option == null ? {} : option);
			
			var buttonOK = $('<button></button>').attr('name', 'ok').attr('id', 'ok').attr('class', 'ui-state-default ui-corner-all').click(function() {

				alertForm.dialog('close');
				
				if ($.isFunction(option.clickOK))
					option.clickOK.apply();
			});
			
			var tagP = alertForm.find('p');
			if(option.dialog == null || option.dialog.length == 0)
				tagP.html(buttonOK.html('OK'));
			else
			{
				tagP.html(buttonOK.html(option.dialog[0]));
				
				if(option.dialog.length == 2)
				{
					var buttonCancel = $('<button></button>').attr('name', 'cancel').attr('id', 'cancel').attr('class', 'ui-state-default ui-corner-all').click(function() {
						alertForm.dialog('close');
						
						if ($.isFunction(option.clickCancel))
							option.clickCancel.apply();
					});
					tagP.css('text-align', 'right').append('&nbsp;&nbsp;').append(buttonCancel.html(option.dialog[1]));
				}
			}
			
			if(isModal == true)
				$this.dialog('option', 'show', null).dialog('option', 'hide', null);
			
			alertForm.find('#conteudo').html(msg);
			if(alertForm.dialog('isOpen') == null)
			{
				alertForm.dialog({
					width: (option.width == null ? '300' : option.width),
					height: 'auto',
					minHeight: '0',
					minWidth: '300',
					zIndex: '19000',
					dialogClass: 'janelaAlert',
					resizable: false,
					modal: true,
					close: function(event, ui) {
						if(isModal == true)
							$this.dialog('close').dialog('open');
							
						$(this).dialog('destroy')/*.remove()*/;
						$('#menuBar').css('z-index', '20000');
					},
					drag: function(event, ui) { },
					dragStart: function(event, ui) { $(this).hide(); alertHeader.css('border-bottom', '1px solid #666'); },
					dragStop: function(event, ui) { $(this).show(); alertHeader.css('border-bottom', '0px'); },
					open: function(event, ui) {
						$(this).parents('.ui-dialog').changeClass('ui-widget-content-disabled', '.ui-widget-content').find('.ui-dialog-titlebar').changeClass('ui-widget-header-disabled', '.ui-widget-header');
						$(this).find('#conteudo').css('opacity', 1.0);
					},
					title: titulo
				});
			}
			else
				alertForm.dialog('open').dialog('option', 'title', titulo);
			
			return $this;
		},
		newPage: function(file, param, callback) {
			var $this = $(this);
			if($this.attr('id').indexOf('tab') == -1 && $this.attr('class').indexOf('conteudo') == -1)
				$this = $(this).parent('div');
			
			var elements = $this.find('div[id^="page_"]').hide();
			var newId = (elements.length+1);
			
			var oldPage = $this.find('div#page_'+(newId-1));

			var newPage = $('<div></div>').attr('id', 'page_'+newId);
			newPage.ajaxSend(function(evt, request, settings){
				if(settings.url != "../../LoginServlet?opcao=1")
					$this.find('div#msgCarregando').show();
			 }).load(file, (param == null ? {} : param), function() {
				setTimeout(function() { modulo_ready(newPage, oldPage, $this); }, 200);
				if ($.isFunction(callback))
					callback.apply();
				
			}).ajaxSuccess(function() { $this.find('div#msgCarregando').hide(); }).appendTo($this).hide().fadeIn(600);

			return $this;
		},
		openWindow: function(id, param, link) {
			var $this = $(this);
			param = (param == null ? {} : param);
			
			if(link != true)
			{
				$this.unbind(($this.attr('class') == 'icon' ? 'dblclick' : 'click'));
				param.executeOpen = {reference: $this, id: id, param: param};
			}
			
			$this.sendRequest(param.file, {}, function(data, textStatus) {
				var divPage = $('<div><div>').attr("id", "page_1").html(data);
				var divPrincipal = $('<div><div>').attr("id", id).attr("class", "conteudo").html(divPage)
					.append('<div style="position: absolute; margin: 20% 40%; display: none;" id="msgCarregando">Carregando...</div>');
				
				if(param.pageToReload != null)
				{
					divPrincipal.hide().appendTo(param.pageToReload).fadeIn(1000);
					setTimeout(function() {
						if ($.isFunction(param.moduloCallback))
							param.moduloCallback.apply();
					}, 200);
				}
				else					
					divPrincipal.appendTo(desktop).showDialog(param);
			});
		},
		createOption: function(name, subService) {
			var link = $('<a></a>').attr('href', '#').html(name + (subService == true ? ' <span>&#187;</span>' : ''));
			$(this).append($('<li></li>').append(link));

			return link;
		},
		createIcon: function(name, img, option)
		{
			var link = $('<a></a>').attr('href', '#').attr('class', 'icon').click(function(e) { e.preventDefault(); }).html('<img src="../../images/icons/'+img+'" alt=""/><span>'+name+'</span>').disableSelection().appendTo($(this));
			
			if(option != null)
			{
				if(option.top != null)
					link.css('top', option.top);
					
				if(option.left != null)
					link.css('left', option.left);
					
				if(option.position != null)
					link.css('position', option.position);
			}
			link.draggable({
				opacity: .75,
				refreshPositions: true,
				containment: desktop
			});
			
			return link;
		},
		onlyNumber: function() {
			return $(this).keypress(function (e) {
			  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
			    return false;
			});
		},
		isEmail: function() {
			var regmail = /^[\w!#$%&amp;'*+\/=?^`{|}~-]+(\.[\w!#$%&amp;'*+\/=?^`{|}~-]+)*@(([\w-]+\.)+[A-Za-z]{2,6}|\[\d{1,3}(\.\d{1,3}){3}\])$/;
			return (regmail.test($(this).val()));	
		},
		sendRequest: function(param1, param2, param3)
		{
			var full = false;
			var $this = $(this).parent('div');
			if($this.parent('div').attr('id') == '')
			{
				$this = $this.parent('div');
				full = true;
			}else
			{
				var cont = $this.find('.conteudo')
				if(cont.html() != null)
					full = true;
			}
			
			var div = $('<div id="sending" style="width: '+(full ? '100%' : $this.css('width'))+'; height:'+(full ? '100%' : $this.css('height'))+';"><img src="../../images/ajaxloading.gif" alt=""/></div>');
			$this.prepend(div);

			if($.isFunction(param2))
			{
				$.post(param1, function(data, textStatus) {
					div.clear();
					param2(data, textStatus);
				});
			}else if($.isFunction(param3))
			{
				$.post(param1, param2, function(data, textStatus) {
					div.clear();
					param3(data, textStatus);
				});
			}else if(param2 != null)
			{
				$.post(param1, param2, function(data, textStatus) {
					div.clear();
				});	
			}else if(param1 != null)
			{
				$.post(param1, function(data, textStatus) {
					div.clear();
				});	
			}
			
			return {this: this, telaLoading: div};
		}
	});
	
	var request = function()
	{
		return {
			get:function(id, params)
			{
				var params = (params == null ? window.location.hash : params).split('#')[1];
				var param = null;
				if(params != null)
				{
					if(params.indexOf('&') == -1)
					{
						param = params.split('=');
						if(param[0] == id)
							return param[1];
					}else
					{
						params = params.split('&');
						for (var index in params)
						{
							param = params[index].split('=');
							if(param[0] == id)
								return param[1];
						}
					}
				}
				return null;
			},
			html:function(object, param, file, clear, callback)
			{
				$.post(file, (param == null ? {} : param), function(data, textStatus) {
					if(clear != false)
						object.html(data);
					else
						object.append(data);
						
					if ($.isFunction(callback))
						callback.apply();
				});
				
				return object;
			}
		};
	}();
		
	var criarBotaoEditar = function(object)
	{
		var link = $('<a></a>').attr('href', '#').html('<img src="../../images/icons/b_edit.png" alt="Editar"></a>');
		object.html(link);
		return link;
	};
	
	var criarBotaoExcluir = function(object)
	{
		var link = $('<a></a>').attr('href', '#').html('<img src="../../images/icons/delete.gif" alt="Excluir"></a>');
		object.html(link);
		return link;
	};
	
	/* by Desconhecido */
	var sleep = function(milliseconds) {
		var start = new Date().getTime();
		for (var i = 0; i < 1e7; i++)
		{
			if ((new Date().getTime() - start) > milliseconds)
			break;
		}
	};
	
	var rand = function(l, u)
	{
		return Math.floor((Math.random() * (u-l+1))+l);
	};
	
	function trim(str){return str.replace(/^\s+|\s+$/g,"");}
