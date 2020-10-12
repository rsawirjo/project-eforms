<%--
  Copyright 2013-2020 Hippo B.V. (http://www.onehippo.com)
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%--@elvariable id="form" type="com.onehippo.cms7.eforms.hst.model.Form"--%>
<%--@elvariable id="eforms_errors" type="java.util.Map<String, com.onehippo.cms7.eforms.hst.model.ErrorMessage>"--%>

<hst:defineObjects />

<c:if test="${processDone}">
    <p>${afterProcessSuccessText}</p>
</c:if>
<c:if test="${not empty form.title}">
  <h2><c:out value="${form.title}" /></h2>
</c:if>

<c:if test="${not empty formIntro}">
  <p><c:out value="${formIntro}" /></p>
</c:if>

<c:set var="style">
  <c:if test="${empty eforms_errors}">display:none;</c:if>
</c:set>
<div id="feedbackPanel" class="nojs-error" style="${style}">
  <ul>
    <c:forEach items="${eforms_errors}" var="error">
      <li><c:out value="${error.value.localizedMessage}"/></li>
    </c:forEach>
  </ul>
</div>

<c:if test="${maxFormSubmissionsReached}">
  <c:choose>
    <c:when test="${not empty maxFormSubmissionsReachedText}">
      <p><c:out value="${maxFormSubmissionsReachedText}" /></p>
    </c:when>
    <c:otherwise>
      <p>The maximum number of submission for this form has been reached</p>
    </c:otherwise>
  </c:choose>
</c:if>

<c:if test="${not maxFormSubmissionsReached}">

  <form class="form" action="<hst:actionURL />" method="post" name="${form.name}" <c:if test="${form.multipart}">enctype="multipart/form-data"</c:if>>

    <c:set var="formPages" value="${form.pages}" />

    <c:if test="${fn:length(formPages) gt 1}">
      <ul id="pagesTab" class="eforms-pagetab" style="DISPLAY: none">
        <c:forEach var="page" items="${formPages}" varStatus="status">
          <c:choose>
            <c:when test="${status.index eq 0}">
              <li class="conditionally-visible selected"><c:out value="${page.label}" /></li>
            </c:when>
            <c:otherwise>
              <li class="conditionally-visible"><c:out value="${page.label}" /></li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </ul>
    </c:if>

    <c:forEach var="page" items="${formPages}" varStatus="status">

      <div id="page${status.index}" class="eforms-page conditionally-visible">

        <c:forEach var="fieldItem" items="${page.fields}">
          <c:choose>
            <c:when test="${fieldItem.type eq 'fieldgroup'}">
              <c:set var="groupCssClassName" value="eforms-fieldgroup" />
              <c:if test="${fieldItem.oneline}">
                <c:set var="groupCssClassName" value="eforms-fieldgroup oneline" />
              </c:if>
              <fieldset name="${fieldItem.fieldNamePrefix}" class="${groupCssClassName}">
                <c:if test="${not empty fieldItem.label}">
                  <legend class="eforms-fieldgroupname"><c:out value="${fieldItem.label}"/></legend>
                </c:if>
                <c:forEach var="fieldItemInGroup" items="${fieldItem.fields}">
                  <tag:eformsrenderfield field="${fieldItemInGroup}"/>
                </c:forEach>
                <span class="eforms-hint"><c:out value="${fieldItem.hint}"/></span>
              </fieldset>
            </c:when>
            <c:otherwise>
              <tag:eformsrenderfield field="${fieldItem}"/>
            </c:otherwise>
          </c:choose>

        </c:forEach>

      </div>

    </c:forEach>

    <div class="eforms-buttons">
      <c:forEach var="button" items="${form.buttons}">
        <c:choose>
          <c:when test="${button.type eq 'nextbutton'}">
            <input id="nextPageButton" type="button" name="nextPageButton" class="${button.styleClass}" style="display: none"
                   value="<c:choose><c:when test='${empty button.value}'><c:out value='${button.name}'/></c:when><c:otherwise><c:out value='${button.value}' /></c:otherwise></c:choose>" />
          </c:when>
          <c:when test="${button.type eq 'previousbutton'}">
            <input id="previousPageButton" type="button" name="previousPageButton" class="${button.styleClass}" style="display: none"
                   value="<c:choose><c:when test='${empty button.value}'><c:out value='${button.name}'/></c:when><c:otherwise><c:out value='${button.value}' /></c:otherwise></c:choose>" />
          </c:when>
          <c:when test="${button.type eq 'resetbutton'}">
            <input type="reset" name="${button.formRelativeUniqueName}" class="${button.styleClass}"
                   value="<c:choose><c:when test='${empty button.value}'><c:out value='${button.name}'/></c:when><c:otherwise><c:out value='${button.value}'/></c:otherwise></c:choose>" />
          </c:when>
          <c:when test="${button.type eq 'submitbutton'}">
            <input type="submit" name="${button.formRelativeUniqueName}" class="${button.styleClass}"
                   value="<c:choose><c:when test='${empty button.value}'><c:out value='${button.name}'/></c:when><c:otherwise><c:out value='${button.value}' /></c:otherwise></c:choose>" />
          </c:when>
          <c:otherwise>
            <input type="button" name="${button.formRelativeUniqueName}" class="${button.styleClass}"
                   value="<c:choose><c:when test='${empty button.value}'><c:out value='${button.name}'/></c:when><c:otherwise><c:out value='${button.value}'/></c:otherwise></c:choose>" />
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </div>

  </form>

</c:if>

<%--
    //########################################################################
    //  HEADER CONTRIBUTIONS
    //########################################################################
--%>

<hst:headContribution keyHint="formValidationCss">
  <link rel="stylesheet" href="<hst:link path="/js/formcheck/theme/blue/formcheck.css"/>" type="text/css" />
</hst:headContribution>

<hst:headContribution keyHint="jqueryUICss">
  <link rel="stylesheet" href="<hst:link path="/css/jquery-ui-1.10.2.custom.min.css"/>" type="text/css" />
</hst:headContribution>

<hst:headContribution keyHint="jqueryDatetimePickerCss">
  <link rel="stylesheet" href="<hst:link path="/css/jquery.datetimepicker-2.3.7.min.css"/>" type="text/css" />
</hst:headContribution>

<hst:headContribution keyHint="jquery">
  <script type="text/javascript" src="<hst:link path="/js/jquery-1.9.1.min.js"/>"></script>
</hst:headContribution>

<hst:headContribution keyHint="jquery-datepicker">
  <script type="text/javascript" src="<hst:link path="/js/jquery-ui-1.10.2.custom.min.js"/>"></script>
</hst:headContribution>

<hst:headContribution keyHint="jquery-datetimepicker">
  <script type="text/javascript" src="<hst:link path="/js/jquery.datetimepicker.full-2.3.7.min.js"/>"></script>
</hst:headContribution>

<hst:headContribution keyHint="formJsValidation">
  <script type="text/javascript" src="<hst:link path="/js/jquery-validate-1.1.2.min.js"/>"></script>
</hst:headContribution>

<hst:headContribution keyHint="formJsValidationHippo">
  <script type="text/javascript" src="<hst:link path="/js/jquery-hippo-validate.js"/>"></script>
</hst:headContribution>

<hst:headContribution keyHint="formCss">
  <link rel="stylesheet" href="<hst:link path="/css/eforms.css"/>" type="text/css" />
</hst:headContribution>

<script type="text/javascript">
<c:if test="${not empty hstRequestContext.preferredLocale}">
  $.datetimepicker.setLocale('${hstRequestContext.preferredLocale.language}');
</c:if>

$(document).ready(function() {
    $(function() {
      if ($( "#slider" )) {
        $("#submitButton").prop('disabled', true);
        $("#slider").slider({
          stop: function (event, ui) {
            var selection = $("#slider").slider("value");
            var max = $("#slider").slider("option", "max");
            if (selection >= 70) {
              $("#slider").slider("value", max);
              $('#slided').css({'display': 'block'});
              $('#notSlided').css({'display': 'none'});
              $("#submitButton").prop('disabled', false);
            }
            else {
              $("#slider").slider("value", 0);
              $('#slided').css({'display': 'none'});
              $('#notSlided').css({'display': 'block'});
              $("#submitButton").prop('disabled', true);
            }
          }
        });
      };
    });

  $('form[name="${form.name}"]').validate({errorElement:'div'});

  var resetPagesVisible = function() {
    var allPages = $('.eforms-page.conditionally-visible');
    var curPage = $('.eforms-page.conditionally-visible:visible:first');
    var curIndex = -1;

    for (var i = 0; i < allPages.length; i++) {
      if (allPages[i].id == curPage.attr('id')) {
        curIndex = i;
      }
    }

    if (curIndex > 0) {
      $('#previousPageButton').show();
    }

    if (curIndex < allPages.length - 1) {
      $('#nextPageButton').show();
      $('.eforms-buttons input[type="submit"]').each(function() {
        $(this).hide();
      });
    } else if (curIndex == allPages.length - 1) {
      $('#nextPageButton').hide();
      $('.eforms-buttons input[type="submit"]').each(function() {
        $(this).show();
      });
    }

    $('#pagesTab li').hide();
    $('#pagesTab li.conditionally-visible').show();
  };


  <%--
    Function used to create parameters object containing form fields name/value pairs.
    The params object can be posted through ajax for validation.
    --%>
  function addFormFieldsToParameters(fields, params) {
    fields.each(function() {
      var field = $(this);
      var fieldType = field.attr('type');
      var fieldName = field.attr('name');
      var checked = [];
      var checkedSelector = '.eforms-page.conditionally-visible:visible .eforms-field *:input[name="' + fieldName + '"]:checked';

      if (fieldType == 'checkbox') {
        if (!params[fieldName]) {
          checked = $(checkedSelector);
          if (checked.length > 0) {
            var values = [];
            checked.each(function(index) {
              values[index] = $(this).val();
            });
            params[fieldName] = values;
          } else {
            params[fieldName] = '';
          }
        }
      } else if (fieldType == 'radio') {
        if (!params[fieldName]) {
          checked = $(checkedSelector);
          if (checked.length > 0) {
            params[fieldName] = checked.val();
          } else {
            params[fieldName] = '';
          }
        }
      } else {
        params[fieldName] = $(this).val();
      }
    });
  }

  function endsWith(subject, search) {
    var position = subject.length - search.length;
    var lastIndex = subject.indexOf(search, position);
    return lastIndex !== -1 && lastIndex === position;
  }


  <%-- real-time ajax-based single field validation --%>
  var fields = $('.eforms-field *:input');
  var ajaxValidationUrl = '<hst:resourceURL resourceId="validation"/>';
  ajaxValidationUrl = ajaxValidationUrl.replace(/&amp;/g, "&");
  fields.blur(function() {
    // on leaving form field, post field name/value for ajax validation
    var params = {};
    var field = $(this);
    var fieldName = field.attr('name');
    var fieldType = field.attr('type');
    var fieldValue = field.val();
    var otherSuffix = '-other';

    // Radio groups and checkboxes have an option for showing a textfield if users wish to provide a different value
    // than is provided through the checkboxes/radio buttons. This value is always named <fieldName>-other and will only
    // be validated correctly over Ajax if both the field that enables/disables the '-other' textfield as well as the
    // '-other' textfield itself is submitted.
    if (endsWith(fieldName, otherSuffix)) {
      var prevFieldName = fieldName.substring(0, fieldName.length - otherSuffix.length);
      // check if the radio/checkbox that enables this '-other' field is checked
      var prevField = $('.eforms-field *:input[name=' + prevFieldName + '][value=-other]:checked');
      if (prevField.length) {
        params[fieldName] = fieldValue;
        params[prevFieldName] = otherSuffix;
      }
    } else if (fieldType === 'checkbox') {
      var checked = $('.eforms-field *:input[name=' + fieldName + ']:checked');
      if (checked.length > 0) {
        var values = [];
        checked.each(function (index) {
          values[index] = $(this).val();
        });
        params[fieldName] = values;
      } else {
        params[fieldName] = '';
      }
    } else {
      params[fieldName] = fieldValue;
    }

    if (params.length === 0) {
      // No fields to validate
      return;
    }

    $.post(ajaxValidationUrl, params,
      function(data) {
        var feedbackPanel = $('#feedbackPanel');
        var count = 0;
        if (data) {
          var messagesList = $('#feedbackPanel > ul');
          for (var errorKey in data) {
            if (data.hasOwnProperty(errorKey)) {
              // get the error message
              var errorMessage = data[errorKey];
              // remove previous error messages from feedback panel
              messagesList.empty();
              if (errorMessage) {
                // add error message to feedback panel
                messagesList.append('<li>' + errorMessage.localizedMessage + '</li>');
                count++;
              }
            }
          }
        }
        if (count > 0) {
          // make feedback panel visible
          feedbackPanel.show();
        } else {
          // make feedback panel invisible
          feedbackPanel.hide();
        }
      }, "json");
  });


  <%-- Write JSON of field condition infos --%>
  var conditions = ${form.conditionsAsJson};
  var condFieldNames = {};

  if (conditions) {
    var items = [];
    if (conditions['fields']) {
      items = items.concat(conditions['fields']);
    }
    if (conditions['pages']) {
      items = items.concat(conditions['pages']);
    }
    for (var i = 0; i < items.length; i++) {
      var item = items[i];
      var condFieldName = item['condname'];
      if (!condFieldNames[condFieldName]) {
        condFieldNames[condFieldName] = true;
      }
    }
  }

  for (var condFieldName in condFieldNames) {
    var condField = $('.eforms-field *[name="' + condFieldName + '"]');
    if (condField.length == 0) continue;
    var eventType = 'change';

    condField.bind(eventType, function() {
      if (conditions && conditions['fields']) {
        var fields = conditions['fields'];

        for (var i = 0; i < fields.length; i++) {
          var field = fields[i];
          var condFieldName = field['condname'];
          if ($(this).attr('name') != condFieldName) continue;

          var name = field['name'];
          var targetField = $('.eforms-field *[name="' + name + '"]');
          if (targetField.length == 0) {
              targetField = $('.eforms-fieldgroup[name="' + name + '"]');
          }
          if (targetField.length == 0) {
              targetField = $('.eforms-text[name="' + name + '"]');
          }
          if (targetField.length == 0) continue;

          var targetContainer = targetField.parents('.eforms-field');
          if (targetContainer.length == 0) {
              targetContainer = targetField;
          }

          var type = field['condtype'];
          var condFieldValue = field['condvalue'];
          var condNegated = field['condnegated'];
          var curSelectedValue = $(this).val();
          if ($(this).is('input') && $(this).attr('type') == 'radio') {
            curSelectedValue = $('.eforms-field *[name="' + condFieldName + '"]:radio:checked').val();
          }

          if (type == 'visibility') {
            if ((!condNegated && condFieldValue == curSelectedValue)||(condNegated && condFieldValue != curSelectedValue)) {
              targetContainer.show();
            } else {
              targetContainer.hide();
            }
          }
        }

        var pages = conditions['pages'];
        for (var i = 0; i < pages.length; i++) {
          var page = pages[i];
          var condFieldName = page['condname'];
          if ($(this).attr('name') != condFieldName) continue;

          var pageIndex = page['index'];
          var targetPage = $('#page' + pageIndex);
          var type = page['condtype'];
          var condFieldValue = page['condvalue'];
          var condNegated = page['condnegated'];
          var curSelectedValue = $(this).val();
          if ($(this).is('input') && $(this).attr('type') == 'radio') {
            curSelectedValue = $('.eforms-field *[name="' + condFieldName + '"]:radio:checked').val();
          }

          if (type == 'visibility') {
            if ((!condNegated && condFieldValue == curSelectedValue)||(condNegated && condFieldValue != curSelectedValue)) {
              targetPage.addClass('conditionally-visible');
              $('#pagesTab li:nth-child(' + (pageIndex + 1) + ')').addClass('conditionally-visible');
            } else {
              targetPage.removeClass('conditionally-visible');
              $('#pagesTab li:nth-child(' + (pageIndex + 1) + ')').removeClass('conditionally-visible');
            }
            resetPagesVisible();
          }
        }
      }
    });

    condField.trigger(eventType);
  }

  <%-- In order not to show page tab for script-disabled clients, show the tabs by script if exits. --%>
  if ($('#pagesTab')) {
    $('#pagesTab').show();
  }
  <%-- Hide all the pages except of the first page --%>
  $('.eforms-page').each(function() {
    $(this).hide();
  });
  if ($('.eforms-page.conditionally-visible').length) {
    $('.eforms-page.conditionally-visible:first').show();
  }

  resetPagesVisible();

  $('#previousPageButton').click(function() {
    var curPage = $('.eforms-page.conditionally-visible:visible');
    var prevPage = curPage.prevAll('.eforms-page.conditionally-visible:first');
    prevPage.show();
    curPage.hide();

    var curIndex = parseInt(curPage.attr('id').replace(/^page/, ''));
    var prevIndex = parseInt(prevPage.attr('id').replace(/^page/, ''));
    $('#pagesTab li:nth-child(' + (curIndex + 1) + ')').removeClass('selected');
    $('#pagesTab li:nth-child(' + (prevIndex + 1) + ')').addClass('selected');

    if (prevPage.prevAll('.eforms-page.conditionally-visible:first').length == 0) {
      $('#previousPageButton').hide();
    }
    $('#nextPageButton').show();
    $('.eforms-buttons input[type="submit"]').each(function() {
        $(this).hide();
      });

    // remove error messages from feedback panel
    var messagesList = $('#feedbackPanel > ul');
    messagesList.empty();

    // hide feedbackPanel
    var feedbackPanel = $('#feedbackPanel');
    feedbackPanel.hide();

  });

  $('#nextPageButton').click(function() {
    var curPage = $('.eforms-page.conditionally-visible:visible');

    // ajax based validation
    // validate all fields on current page before going to the next
    var params = {};
    var fieldsOnPage = $('.eforms-page.conditionally-visible:visible .eforms-field:visible *:input');
    addFormFieldsToParameters(fieldsOnPage, params);

    // add an empty parameter for any group on the current page
    var groupsOnPage = $('.eforms-page.conditionally-visible:visible .eforms-fieldgroup:visible');
    groupsOnPage.each(function() {
      params[$(this).attr('name')] = '';
    });

    // add current page index to parameters
    params['currentPage'] = curPage.attr('id').replace(/^page/, '');

    $.post(ajaxValidationUrl, params,
      function(data){

        // remove previous error messages from feedback panel
        var messagesList = $('#feedbackPanel > ul');
        messagesList.empty();

        var count = 0;
        if (data) {
          for (var fieldName in data) {
            // get the error message
            var errorMessage = data[fieldName];
            if (errorMessage) {
              // add error message to feedback panel
              messagesList.append('<li>' + errorMessage.localizedMessage + '</li>');
              count++;
            }
          }
        }
        var feedbackPanel = $('#feedbackPanel');
        if (count > 0) {
            // there are validation errors
            // make feedback panel visible
            feedbackPanel.show();
        } else {
          // no error messages
          // make feedback panel invisible
          feedbackPanel.hide();

          // go to the next page
          var nextPage = curPage.nextAll('.eforms-page.conditionally-visible:first');
          nextPage.show();
          curPage.hide();

          var curIndex = parseInt(curPage.attr('id').replace(/^page/, ''));
          var nextIndex = parseInt(nextPage.attr('id').replace(/^page/, ''));
          $('#pagesTab li:nth-child(' + (curIndex + 1) + ')').removeClass('selected');
          $('#pagesTab li:nth-child(' + (nextIndex + 1) + ')').addClass('selected');

          $('#previousPageButton').show();
          if (nextPage.nextAll('.eforms-page.conditionally-visible:first').length == 0) {
            $('#nextPageButton').hide();
            $('.eforms-buttons input[type="submit"]').each(function() {
              $(this).show();
            });
          }

        }

      }, "json");


  });


  var valid = false;

  // ajax page validation in case of last (or only) page
  $('form[name="${form.name}"]').submit(function(event) {

    var curPage = $('.eforms-page.conditionally-visible:visible');

    if ($("#slider")) {
      if($("#slider").slider("option", "value") != $("#slider").slider("option", "max")) {
        return false;
      }
    }

    // if valid flag is set, page was validated and form can be submitted
    if (valid) {
        return true;
    }

    var params = {};
    var fieldsOnPage = $('.eforms-page.conditionally-visible:visible .eforms-field:visible *:input');
    addFormFieldsToParameters(fieldsOnPage, params);

    // add an empty parameter for any visible group on the current page
    var groupsOnPage = $('.eforms-page.conditionally-visible:visible .eforms-fieldgroup:visible');
    groupsOnPage.each(function() {
        params[$(this).attr('name')] = '';
    });

    // add current page index to parameters
    params['currentPage'] = curPage.attr('id').replace(/^page/, '');

    // prevent form submission as we want to do ajax validation first
    event.preventDefault();

    $.post(ajaxValidationUrl, params,
      function(data){

        // remove previous error messages from feedback panel
        var messagesList = $('#feedbackPanel > ul');
        messagesList.empty();

        var count = 0;
        if (data) {
          for (var fieldName in data) {
            // get the error message
            var errorMessage = data[fieldName];
            if (errorMessage) {
              // add error message to feedback panel
              messagesList.append('<li>' + errorMessage.localizedMessage + '</li>');
              count++;
            }
          }
        }
        var feedbackPanel = $('#feedbackPanel');
        if (count > 0) {
            // there are validation errors
            // make feedback panel visible
            feedbackPanel.show();

        } else {
          // no error messages
          // make feedback panel invisible
          feedbackPanel.hide();

          // set valid flag and resubmit form
          valid = true;
          $('form[name="${form.name}"] button:submit').click();
        }

      }, "json");

  });

});
</script>
