/**
 * @OnlyCurrentDoc
 *
 * The above comment directs Apps Script to limit the scope of file
 * access for this add-on. It specifies that this add-on will only
 * attempt to read or modify the files in which the add-on is used,
 * and not all of the user's files. The authorization request message
 * presented to users will reflect this limited scope.
 */

/**
 * Creates a menu entry in the Google Docs UI when the document is opened.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 *
 * @param {object} e The event parameter for a simple onOpen trigger. To
 *     determine which authorization mode (ScriptApp.AuthMode) the trigger is
 *     running in, inspect e.authMode.
 */
function onOpen(e) {
  DocumentApp.getUi().createAddonMenu()
      .addItem('Sign!', 'showSidebar')
      .addToUi();
  DriveApp.getRootFolder();
}

/**
 * Runs when the add-on is installed.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 *
 * @param {object} e The event parameter for a simple onInstall trigger. To
 *     determine which authorization mode (ScriptApp.AuthMode) the trigger is
 *     running in, inspect e.authMode. (In practice, onInstall triggers always
 *     run in AuthMode.FULL, but onOpen triggers may be AuthMode.LIMITED or
 *     AuthMode.NONE.)
 */
function onInstall(e) {
  onOpen(e);
  showSidebar();
}

/**
 * Opens a sidebar in the document containing the add-on's user interface.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 */
function showSidebar() {
  var ui = HtmlService.createHtmlOutputFromFile('Sidebar')
      .setTitle('Sign with Autenti');
  DocumentApp.getUi().showSidebar(ui);
}

function getDocumentsTest() {
  var url = PropertiesService.getScriptProperties().getProperty('mainUrl') + '/documents';
  var response = UrlFetchApp.fetch(url, {'muteHttpExceptions': true});
  var json = response.getContentText();
  var data = JSON.parse(json);
  return data;
}

function getCurrentDocument() {
  var title = DocumentApp.getActiveDocument().getName();
  var id = DocumentApp.getActiveDocument().getId();
  var jstr = '{"title": "' + title +'", "id":"' + id + '"}';
  return JSON.parse(jstr);
}

function getCurrentUser() {
  var email = Session.getEffectiveUser().getEmail();
  var userKey = ScriptApp.getOAuthToken();
  var jstr = '{"email": "' + email +'", "key":"' + userKey + '"}';
  return JSON.parse(jstr);
}

function checkUserInExternalApp() {
  var email = Session.getEffectiveUser().getEmail();
  var url = PropertiesService.getScriptProperties().getProperty('mainUrl') + '/users?email=' + email;
  var response = UrlFetchApp.fetch(url, {'muteHttpExceptions': true});
  var json = response.getContentText();
  var data = null;
  try {
    data = JSON.parse(json);
  } catch(e) {}
  if(data && email === data.email) {
    return true;
  }
  return false;
}

function createAccountInExternalApp() {
  var email = getCurrentUser().email;
  var data = {'email': email};
  var options = {
    'method':'post',
    'contentType':'application/json',
    'muteHttpExceptions': true,
    'payload': JSON.stringify(data)
  };
  var url = PropertiesService.getScriptProperties().getProperty('mainUrl') + '/users';
  var response = UrlFetchApp.fetch(url, options);
  var json = response.getContentText();
  if(json) {
    return true;
  }
  return false;
}

function updateAccessToken() {
  var email = getCurrentUser().email;
  var accessToken = ScriptApp.getOAuthToken();
  var data = {'email': email, 'temporaryAccessToken': accessToken};
  var options = {
    'method':'put',
    'contentType':'application/json',
    'muteHttpExceptions': true,
    'payload': JSON.stringify(data)
  };
  var url = PropertiesService.getScriptProperties().getProperty('mainUrl') + '/users';
  var response = UrlFetchApp.fetch(url, options);
  var json = response.getContentText();
  if(json) {
    return true;
  }
  return false;
}

function pushDocument() {
  var accessToken = ScriptApp.getOAuthToken();
  var fileId = DocumentApp.getActiveDocument().getId();
  var title = DocumentApp.getActiveDocument().getName();
  var data = {'fileId': fileId, 'accessToken': accessToken, 'title': title};
  var options = {
    'method':'post',
    'contentType':'application/json',
    'muteHttpExceptions': true,
    'payload': JSON.stringify(data)
  };
  var url = PropertiesService.getScriptProperties().getProperty('mainUrl') + '/documents';
  var response = UrlFetchApp.fetch(url, options);
  var json = response.getContentText();
  if(json) {
    return true;
  }
  return false;
}
