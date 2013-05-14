// showcomments.js v0.1
//
// Copyright (c) 2008 Peter Yim (http://www.ibm.com/developerworks)
// 
/*--------------------------------------------------------------------------*/

// default to english
if (typeof(dwc) == 'undefined') {
  dwc = {};
  dwc.signInPreText = '';
  dwc.signIn = 'Sign in';
  dwc.or = 'or';
  dwc.register = 'register';
  dwc.signInPostText = '';
  dwc.leaveCmt = 'to leave a comment.';
  dwc.addCmts = 'Add comments';
  dwc.addCmt = 'Add comment';
  dwc.view = 'View';
  dwc.totalCmts = 'Total comments';
  dwc.postYourCmt = 'Post your comment';
  dwc.show = 'Show:';
  dwc.recentCmts = 'Most recent comments';
  dwc.allCmts = 'All comments';
  dwc.tooLongCmt = 'Your comment has exceeded the 1000-char limit';
  dwc.numCharsLeft = '<span id="charCount">{1}</span> characters left';
  dwc.reportAbuse = 'Report abuse';
  dwc.postingCmt = 'Posting comment...';
  dwc.noCmt = 'Be the first to add a comment';
  dwc.netwkErr = 'There is a problem in retrieving the comments.  Please refresh the page later.';
  dwc.btnPost = 'Post';
  dwc.enterCmt = 'Error:  Please add a comment.';
  dwc.loginErr = 'Your login status cannot be verified at this point. Please try again later.';
  dwc.postErr = 'Your comment cannot be posted at this time.  Please try again later.';
  dwc.postBy = 'Posted by <strong>{1}</strong> on {2}'; // {1} is the author to be substituted; {2} is the date
  dwc.siteId = 1;
  dwc.lang = 'en';
  dwc.viperLang = 'en';
  dwc.notifyMsg = 'Notify me when a comment is added';
  dwc.htmlWarning = 'Note: HTML elements are not supported within comments.';
  dwc.doNotErase = '--- Add any comments below this line ---';
  dwc.abuseHref = null;
  dwc.cmt = 'Comment:';
}


jQuery.fn.showComments = function(width,hitsPerPage,nCmtsId, anchorCmts) {
     var _divId = this.id || 'threadShow';  // div id to stick the comments in. Default is <div id="threadShow" />
     var _width = width || '95%';  // width of the comments section
     var _hitsPerPage = hitsPerPage || 10;   // # of hits per page in paging. Default is 5 comments per page
     var _nCmtsId = nCmtsId || 'nCmts';  // span id to stick the # of comments in the span. Default is <span id="nCmts" />
     // Modified the inline anchor (jpp)
     var _anchorCmts = anchorCmts || 'icomments'; // inline anchor for the comment section.  Returns user to comment section after log-in. Default is <anchor href="icomments"/>
     var _PV = [];
     var total = 0;
	 var hash = 0;  // used for opt-in request
	 var maxChars = 1000;
	 var recentNumCmts = 5;
	 var alotCmts = 10;
	 var loginStatus = false;
	 var signinType = 'overlay';
	 //var cachedCommentsUrl = '/developerworks/maverick/jsp/getComments.jsp'; // new url provided by chetan. caching is enabled
	 //var noCachedCommentsUrl = '/developerworks/forums/comment.jspa';  // old url
	 var cachedCommentsUrl = '/developerworks/intdw/forums/actions/getComments.jsp';
	 var noCachedCommentsUrl = '/developerworks/intdw/forums/actions/getComments.jsp';
	 var commentsUrl = cachedCommentsUrl;   // store which url to use
	 
	 jQuery.noConflict();

     // Use jQuery via jQuery(...)
     jQuery(document).ready(function($){
        var divs = [];
		var marker = 0; // marker to paging. 
		var errFlag = 0; // 1 is error

		// display loading image
		var imgPlHdr = '<img id="imgPlHdr" src="//dw1.s81c.com/developerworks/i/spaces/onex/animateloader_onex.gif" />';
		
		var loadMeta = function() {
			 _PV = $.makeArray($('div.metavalue'));
			 var tPV = {};
			 $.each(_PV, function() {
				 var t = jQuery(this).text().split("=");  
				   tPV[t[0]] = encodeURIComponent(t[1]);  // encode value
				});
			 _PV = tPV;
		};

		var storeCmts = function(xmlData) { 
		   divs = [];
			   total = $('totalCount',xmlData).text();
			   if (!total) total = 0;  // total is undefined or null
		   $('comment',xmlData).each(
			function(i) { 
			   var cmt = thFormat(this);
			   divs.push(cmt);
			});
		};
			
		var retrieveCmts = function(callbk,displayAll) { 
			var endIdx = (typeof displayAll != 'undefined' && displayAll == 'true') ? total : marker + _hitsPerPage;
			endIdx = (total != 0 && endIdx > total) ? total : endIdx;  // sanity check
			marker += _hitsPerPage; // move marker
			marker = (total != 0 && marker > total) ? total : marker; // correct overflow	                      
			fetchCmts(0, endIdx, function() {
				// stick the # of comments in the NumCmts placeholder
				$('#numCmts, #numCmts2').html('' + total);
				$('#'+_nCmtsId).html(total + '&nbsp;(<a class="dw-view-comment" href="#icomments">' + dwc.view + '</a>&nbsp;|&nbsp;<a ' + ((loginStatus != 'true') ? 'onclick="window.location=\''+location.href.replace(/(#icomments)/g,'').replace(/#/g,'') + '#icomments\';showSignIn();return false;" href="#icomments"' : 'onclick="jQuery(\'#newCmt\').focus();return false;" href="#icomments"') + '>' + dwc.addCmt + '</a>' + ((loginStatus != 'true') ? ' - ' + dwc.signIn : '') + ')') ; 				
				if (total == 0) {
					$('#topControl, #bottomControl').hide();
					(errFlag) ? $('div#cmtSect').html(errorNwkMsg()) : $('div#cmtSect').html(emptyCmt());  // for comment section
				} 
				if (total > recentNumCmts) {
					$('#dropdownCmts, #dropdownCmts2').show();
					// add the all comments option if there are more than recentNumCmts. Add the option only once.
					if (typeof($("#topControl option[value='all']").val()) == 'undefined') {
						$('#topControl select, #bottomControl select').append($('<option></option>').val('all').html(dwc.allCmts));
					}
				}
				
				// be sure to remove wait image 
				$('#imgPlHdr').remove();
				callbk();
			});
		};

		var postingCmt = function(bAnon) {
			if (validateCmt()) {
			   $.ajax({
				type: "POST",
				//url: "/developerworks/forums/postcomment.jspa",
				url: "/developerworks/intdw/forums/actions/postcomment.jsp",
				data: "contentID=" + _PV['ArticleID'] + "&cn=test" +
				  ((bAnon != undefined && bAnon != null && typeof(bAnon) == 'boolean') ? "&an="+bAnon : "") + 
						  "&cb="+encodeURIComponent($('#newCmt').attr('value').replace(/(\n){3,}/g,'\n\n').replace(/>/g,'&gt;').replace(/</g,'&lt;').replace(/\[\s*((.+\s*\|\s*)?(http[s]?:\/\/|ftp:\/\/)?(www\.)?[a-zA-Z0-9-\.]+\..+(\s*.+\s*\|\s*)?)\]/g,'$1').replace(/\[\s*([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+\s*\]/g,'$1')) + "&rn="+Math.random() + "&siteID=" + dwc.siteId,
					dataType: "xml", 
				beforeSend: function() {
					$('#infoCmt').html('<p class="ibm-ind-link"><a class="ibm-error-link" href="#">' + dwc.postingCmt + '</a></p>');
					$(':button#postCmt').attr('disabled', true);
				},
				complete: function() {
					$('#newCmt,:button#postCmt').removeAttr('disabled');
					$('#infoCmt').text('');
				},
				success: function(xmlRep) {
					if ($('response',xmlRep).text() == 'ok') {
						$('#newCmt').attr('value','');
						$('div#cmtSect').html('');  // clear the comment section
						$('#charCount').html(maxChars);
						commentsUrl = noCachedCommentsUrl;
						init();
					} else {
						if ($('error',xmlRep).text() != '') {
							alert($('error',xmlRep).text());
						} else {
							alert($('response',xmlRep).text());
						}
					}
				},
				error: function() {
					alert(dwc.postErr);	               
				},
				timeout: 60000
		   });
			}
		};

		var validateCmt = function() {
		   var b = false;
		   // disable input fields
		   $('#newCmt').attr('disabled', true);
		   $('#newCmt').attr('value',$.trim($('#newCmt').attr('value'))); 
		   if ($('#newCmt').attr('value') == undefined || $('#newCmt').attr('value') == '') {
			  $('#infoCmt').html('<p class="ibm-ind-error"><strong>' + dwc.enterCmt + '</strong></p>');
			  $('#cmtHead').addClass('ibm-error');
			  $('#newCmt').attr('disabled', false);
		   } else if ($('#newCmt').attr('value').length > maxChars) {
			  $('#infoCmt').html('<p class="ibm-ind-error"><strong>' + dwc.tooLongCmt + '</strong></p>');
			  $('#newCmt').attr('disabled', false);
		   } else {
			  b = true;
		   }
		   
		   return b;  	
		};

		var fetchCmts = function(start,range, callbk) { 
		   $.ajax({
			type: "GET",
			url: commentsUrl,
			data: "contentID="+_PV['ArticleID']+"&start="+start+"&range="+range+"&rn="+Math.random()+"&siteID="+dwc.siteId,
			dataType: "xml",
			success: function(xmlData) {
					storeCmts(xmlData);
					$('div#cmtSect').html(divs.join(''));
					$('.dw-report-abuse').click(function() {
						if (loginStatus != 'true') {
							// save the abuse link until the user is logged in. In afterSignIn callback, the link will be followed
							dwc.abuseHref = this.href;
							showSignIn();							
						} else {
							window.open (this.href, "_newtab");
						}
						return false;
					});
			},
			error: function(xmlData) {
				if (xmlData.responseText.indexOf('Error 500: Cannot find a thread') == 0) { 
					$('div#cmtSect').html(emptyCmt());
				} else {
					errFlag = 1;	
					$('div#cmtSect').html(errorNwkMsg());
				}
			},
			complete: function() {
				callbk();
				(loginStatus == 'true') ? dwc.afterSignIn(signinType) : dwc.afterSignOut();// possibly the custom event got fired before the comment html dom is loaded.
			},
			async: true 
		   });
		};

		var isLogin = function() {
		   var loggedIn = false;
		   
		   $.ajax({
			async: false,
			dataType: 'json',
			url: '/developerworks/dwwi/jsp/Auth.jsp?format=json&rn='+Math.random(), 
			success: function(data) {
				if (typeof(data.status) != 'undefined') {
					loggedIn = data.status;
				}
			}
		   });
		   return loggedIn;
		};

		
		// individual comment
		var thFormat = function (thObj) {		
			var anchorTxt = '';    
			if ($('profileurl',thObj).text().length>0) 
				anchorTxt = '<a href="' + $('profileurl',thObj).text() + '">' + $('author', thObj).text() + '</a>';
			else
				anchorTxt = $('author', thObj).text();
			
			//if (typeof console != 'undefined') console.log('before = ' + $('body',thObj).text().replace(/&lt;(\/*strong|\/*pre)&gt;/ig,'<$1>').replace(/[\r\n]/g, ''));
			// unescape <strong> and <pre>; don't put <pre>...</pre> in <p>...</p>; 
			// first match ...<pre>...</pre>; then match <pre>...</pre>... but not <pre>...<pre><p>...</p>
			// get rid of extra <p></p>
			var body = $('body',thObj).text().replace(/&lt;(\/*strong|\/*pre)&gt;/ig,'<$1>').replace(/[\r\n]/g, '').replace(/(.*?)(<pre>.*?<\/pre>)/ig,'<p>$1</p>$2').replace(/((<pre>.*?<\/pre>)+)((?!<p>).+|$)/ig,'$1<p>$3</p>').replace(/<p><\/p>/gi,'').replace(/<p><br \/>\s*/gi,'<p>').replace(/\s*<br \/><\/p>/gi,'</p>').replace(/<pre><br \/>\s*/gi, '<pre>');
			body = (body.search(/<pre>.*?<\/pre>/g) == -1) ? '<p>'+body+'</p>' : body;
			//if (typeof console != 'undefined')  console.log('after= ' + body);
			var reportAbuseUrl = 'https://www.ibm.com/developerworks/community/report?'
								 + 'lang=' + dwc.viperLang
								 + '&referingURL=' + window.location.href
								 + '&mymessage=' + dwc.cmt + ' ' + encodeURIComponent(body.replace(/<.*?>/g,'')) + '%0D'
								 + encodeURIComponent(dwc.postBy.replace(/\{1\}/, $('author', thObj).text()).replace(/\{2\}/, $('time',thObj).text()).replace(/<.*?>/g,'').substring(0,150)) + '%0D%0D'
								 + encodeURIComponent(dwc.doNotErase);


			return  '<div class="comment"><div class="dw-icomment-container">' +
					  '<div class="dw-icomment-body dw-noborder">' +
					  '<div class="dw-icomment-body dw-noborder">' + body +
					  '<p class="ibm-item-note-alternate dw-item-note-alternate-left">' + 
					  dwc.postBy.replace(/\{1\}/, anchorTxt).replace(/\{2\}/, $('time',thObj).text()) +
					  '</p><p class="ibm-ind-link ibm-item-note-alternate dw-item-note-alternate-right">' +
					  '<a class="ibm-caution-link dw-report-abuse" href="' + reportAbuseUrl + '" target="_newtab">' +
					  dwc.reportAbuse + '</a></p>' +
					  '<div class="dw-clear-both"></div>' +
					  '</div></div>' +
					  '</div></div>';
		};

		var emptyCmt = function() {
		   return  '<div class="comment"><div class="dw-icomment-container">' +
			  '<table class="dw-icomment-body" cellpadding="0" cellspacing="0" width="100%"><tr><td><p>' +
			dwc.noCmt + 
		   '</p></td></tr></table>' +
			  '</div></div>';  
		};

		var errorNwkMsg = function() {
		   return '<div class="comment"><div class="dw-icomment-container">' +
			  '<table class="dw-icomment-body" cellpadding="0" cellspacing="0" width="100%"><tr><td><p>' +
			dwc.netwkErr + 
		   '</p></td></tr></table>' +
			  '</div></div>';  
		};

			// add comment form
		var addCmtForm = function() {
			var s = '<div class="ibm-container ibm-alternate-two dw-commentbox dw-noborder">' +
					'<p id="cmtHead"><label for="newCmt"><strong>' + dwc.addCmt + ':</strong></label></p>' +
					'<div class="ibm-container-body dw-commentbox dw-noborder">';
			
			if (loginStatus != 'true') {
				s += '<p id="signInSect">' + dwc.signInPreText + ' <a onclick="showSignIn();return false;" href="#">' + dwc.signIn + 
				 '</a> ' + dwc.or + ' <a href="/developerworks/dwwi/DWAuthRouter?m=register&lang='+dwc.lang+'&d=' +
				 encodeURIComponent(window.location + '#' + _anchorCmts) + '">' + dwc.register + '</a> ' + dwc.signInPostText + ' ' +
				 dwc.leaveCmt + '</p>';
			} 
			
			s += '<p>' + dwc.htmlWarning + '</p><span id="infoCmt" />' +
			'<form focus="name" enctype="multipart/form-data" method="post" action="" name="form" class="ibm-column-form">' +
			'<p>' +
			'<textarea cols="120" rows="5" name="newCmt" id="newCmt" class="dw-inoperable"></textarea><br /></p>' +
			
			'<span class="ibm-input-group">' +
            '<input type="checkbox" value="1" name="comment_notification" id="comment_notification" disabled="">' +
			'<label id="notifyText" class="ibm-form-note dw-btn-cancel-sec" for="comment_notification">' +
			dwc.notifyMsg +
			'</label>' + 
			'</span>' +
			'<span class="ibm-form-note dw-btn-cancel-sec notifySection" id="maxCharsCount">' +
			 dwc.numCharsLeft.replace(/\{1\}/, maxChars) + '</span>' +
			
			/*
			'<div id="notifiedSect">' +
			'<label for="comment_notification">' + 
			'<span>' + 
			'<input type="checkbox" name="comment_notification" id="comment_notification" value="1" disabled/>' + 
			'<span id="notifyText" class="ibm-form-note">' +
			dwc.notifyMsg +
			'</span>' +
			'</span></label>' +
			'<span id="maxCharsCount" class="ibm-form-note">' + dwc.numCharsLeft.replace(/\{1\}/, maxChars) + '</span>' +
			'</div>' + */
			
			'<br /><br />' +
			'<p>' + 
			'</p>' +   
			'<p class="ibm-buttons-row">' +
			'<input type="button" value="' + dwc.btnPost + '" jquery1275054459750="11" name="postCmt" id="postCmt" class="ibm-btn-arrow-sec dw-btn-arrow-sec" alt="' + dwc.postYourCmt + '"/><br /><br />' +
			'</p>' +
			'</form>' +
			'</div>' +
			'</div>';

			return s;
			
		};
	
		var addViewControl = function(divId,formId,numCmtId,dropdownCmtId,selCmtId,display) {
			return '<div id="' + divId + '" class="ibm-container ibm-alternate-two dw-comment-totals" style="' + ((display) ? '' : 'display:none') + '">' +
		'<div class="ibm-container-body">' +
		'<form id="' + formId + '" method="get" action="">' +
			'<p><span class="dw-comment-totals-label"><strong>' + dwc.totalCmts + ' (<span id="' + numCmtId + '"></span>)</strong></span>' +
				 '<span><img src="//dw1.s81c.com/i/c.gif" width="100" height="10" alt="" /></span>' +
				  '<span class="dw-how-many-comments-label" style="display:none" id="' + dropdownCmtId + '"><label for="' + selCmtId + '">' + dwc.show + '</label>&nbsp;' +
						'<select name="comments" id="' + selCmtId + '" class="dw-how-many-comments">' +
							 '<option selected="selected" value="recent">' + dwc.recentCmts + '   </option>' +
						 '</select>' +
						 '<input type="image" class="ibm-btn-go" name="ibm-go" value="Go" src="//dw1.s81c.com/i/v16/buttons/short-btn.gif" alt="Go"/><br />' +
				  '</span>' +
			'</p>' +
		'</form>' +
		'</div>' +
		'</div>';
		};
		
		
		// find out the opt in value
		var getOptIn = function() {
			$.ajax({
				type: "GET",
				url: "/developerworks/maverick/execute/get_opt_in?",
				data: "content_id="+_PV['ArticleID'],
				dataType: "json",
				success: function(json) { 
					if (json['return'] == 1) {
						hash = json['hash'];  // save the hash for posting
						$('#comment_notification').show();
						$('#notifyText').show();
						$('#notifyText').removeClass('ibm-form-note');
						$('input[name=comment_notification]').attr('disabled', false);
						$('input[name=comment_notification]').attr('checked', (json.opt_in == 1)?true:false);
					} else {
						$('#comment_notification').hide();
						$('#notifyText').hide();
					}
				},
				error: function(json) {
					$('#comment_notification').hide();
					$('#notifyText').hide();
				}
			});
		};
		
		var postingOptIn = function() {
			$.ajax({
				type: "POST",
				url: "/developerworks/maverick/execute/save_opt_in",		       
						data: "content_id=" + _PV['ArticleID'] + "&hash=" + hash + "&notify=" + ($('input[name=comment_notification]').attr('checked')?1:0),
						dataType: "json"
			});
		};
		
		// callback function for after-sign-in. The function is in dwc namespace for global access.
		dwc.afterSignIn = function(signinType) { 
			loginStatus = 'true';
			
			// user has clicked on href. need to send the user to the abuse page after logged in
			if (dwc.abuseHref != null) {
				window.open (dwc.abuseHref, "_newtab");
			}
			
			$('#signInSect').hide();
			$('#newCmt').attr('disabled', false);
			$('#postCmt').attr('disabled', false);
			$('#newCmt').removeClass('dw-inoperable').addClass('dw-operable');
			$('#maxCharsCount').removeClass('ibm-form-note');
			$('#'+_nCmtsId).html(total + '&nbsp;(<a class="dw-view-comment" href="#icomments">' + dwc.view + '</a>&nbsp;|&nbsp;<a ' + ((loginStatus != 'true') ? 'onclick="showSignIn();return false;" href="#"' : 'href="#icomments"') + '>' + dwc.addCmt + '</a>' + ((loginStatus != 'true') ? ' - ' + dwc.signIn : '') + ')') ; 
			getOptIn();	 // determine optIn value
			
			// only move the focus to comments if sign in from comments
			(signinType == 'mf') ? '' : $('#newCmt').focus();
		};
		
		dwc.afterSignOut = function() { 
			loginStatus = false;
			$('#signInSect').show();
			$('#newCmt').attr('disabled', true);
			$('#postCmt').attr('disabled', true);
			$('#newCmt').addClass('dw-inoperable').removeClass('dw-operable');
			$('#maxCharsCount').addClass('ibm-form-note');
			$('#'+_nCmtsId).html(total + '&nbsp;(<a class="dw-view-comment" href="#icomments">' + dwc.view + '</a>&nbsp;|&nbsp;<a ' + ((loginStatus != 'true') ? 'onclick="showSignIn();return false;" href="#"' : 'href="#icomments"') + '>' + dwc.addCmt + '</a>' + ((loginStatus != 'true') ? ' - ' + dwc.signIn : '') + ')') ; 			
		};
	

		// load meta values in hidden divs
		loadMeta();
		
		// check login status
		//loginStatus = isLogin(); 
		
		// display comments
		var s = '';
		s += addCmtForm();
		s += addViewControl('topControl','selectComments','numCmts','dropdownCmts','howManyComments',true);
		s += '<div id="cmtSect"></div>';
		s = imgPlHdr + '<div id="cmtTog" style="display:none">' + s + '</div>';
		s += addViewControl('bottomControl','selectComments2','numCmts2','dropdownCmts2','howManyComments2',false);
		
		// put comments into the div placeholder
		$('div#'+_divId).html(s);
		
		var loggedInCallback = function(event, evtArgs) {
									if (typeof(evtArgs.json) != 'undefined') {  
										loginStatus = evtArgs.json.status;
										
										// grey out post comment section is not logged in
										if (loginStatus != 'true') {
											$('#newCmt').attr('disabled', true);
											$('#postCmt').attr('disabled', true);
										} else {
											signinType = evtArgs.json.signin_type;
											dwc.afterSignIn(signinType);
										}
									}
								};
								
		if (typeof(dwsi) != 'undefined') { 
			dwsi.dwsiEvtTgt.addListener("dwsi_logged_in_onpgload", loggedInCallback);			
			dwsi.dwsiEvtTgt.addListener("dwsi_logged_in", loggedInCallback);
			dwsi.dwsiEvtTgt.addListener("dwsi_logged_out", function() { dwc.afterSignOut();});
		}
		
		
		
		// load comments
		init = function() { 
		   marker = 0; total = 0;
		   retrieveCmts(function() { $('#cmtTog').show(); });
		};
		
		init();
		
		// attach events to elements
		$('#selectComments, #selectComments2').submit(function(event) { 
			commentsUrl = cachedCommentsUrl;
			
			// sync both nodes
			(event.target.id == 'selectComments') ?
				$('#howManyComments2').val($('#howManyComments').val()) :
					$('#howManyComments').val($('#howManyComments2').val());
					
			$('div#cmtSect').append(imgPlHdr);  // add wait image 			
			if ($('#howManyComments, #howManyComments2').val() == 'all') {
				retrieveCmts(function(){},'true');
				if (total >= alotCmts) {
					$('#bottomControl').show();
				}
			} else if ($('#howManyComments, #howManyComments2').val() == 'recent') {
				$('#bottomControl').hide();
				init();
			}
			return false;
		});
		
		var keyCount = function(){
			if ($(this).val().length > maxChars) {
				$(this).val($(this).val().substring(0,maxChars));
			}
			 $('#charCount').html((maxChars - $(this).val().length));
		 };
		 
		$('#newCmt').keydown(keyCount);  
		 
		 $('#newCmt').keyup(keyCount); 
		
		$(':button#postCmt').click(function() {
		   $('#cmtHead').removeClass('ibm-error');
		   postingOptIn();
		   postingCmt();
		   return false;
		});
		
     });  // end ready()
     return jQuery;
};
