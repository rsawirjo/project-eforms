<%--
  Copyright 2014-2018 Hippo B.V. (http://www.onehippo.com)
  --%>
<%@ tag description="render form field item" pageEncoding="UTF-8" %>
<%@ attribute name="field" type="com.onehippo.cms7.eforms.hst.model.AbstractField" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="fieldCssClass" value="eforms-field"/>
<c:if test="${not empty field.extraCssClass}">
  <c:set var="fieldCssClass" value="eforms-field ${field.extraCssClass}"/>
</c:if>

<c:choose>

  <c:when test="${field.type eq 'simpletextfield'}">
    <div class="eforms-text<c:if test="${not empty field.extraCssClass}"> ${field.extraCssClass}</c:if>" name="${field.formRelativeUniqueName}">
      <div class="${field.styleClass}"><c:out value='${field.label}'/></div>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'textfield'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <input type="text" name="${field.formRelativeUniqueName}" class="${field.styleClass}" value="${field.value}"
             <c:if test='${field.length gt 0}'>size="${field.length}"</c:if> <c:if test='${field.maxLength gt 0}'>maxlength="${field.maxLength}"</c:if>
             <c:if test='${field.autocomplete}'> autocomplete="${field.autocomplete}"</c:if>/>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'passwordfield'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <input type="password" name="${field.formRelativeUniqueName}" class="${field.styleClass}"
             <c:if test='${field.length gt 0}'>size="${field.length}"</c:if> <c:if test='${field.maxLength gt 0}'>maxlength="${field.maxLength}"</c:if> />
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'textarea'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <textarea name="${field.formRelativeUniqueName}" class="${field.styleClass}"
                cols="${field.cols}" rows="${field.rows}"
                <c:if test='${field.autocomplete}'> autocomplete="${field.autocomplete}"</c:if>
                <c:if test='${field.minLength gt 0}'>minlength="${field.minLength}"</c:if> <c:if test='${field.maxLength gt 0}'>maxlength="${field.maxLength}"</c:if>>${field.value}</textarea>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'dropdown'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <select name="${field.formRelativeUniqueName}" class="${field.styleClass}"<c:if test='${field.autocomplete}'> autocomplete="${field.autocomplete}"</c:if>>
        <c:forEach var="option" items="${field.options}">
          <option value="<c:out value='${option.value}'/>" <c:if test='${option.selected}'>selected="selected"</c:if>><c:out value="${option.text}"/></option>
        </c:forEach>
      </select>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'fileuploadfield'}">
    <%--@elvariable id="field" type="com.onehippo.cms7.eforms.hst.model.FileuploadField"--%>
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <input type="file" name="${field.formRelativeUniqueName}" data-validate="fileSizeAndExtension" data-max-size="${field.maxUploadSize}" data-allowed-extensions="${field.fileExtensions}" class="${field.styleClass}" />
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'datefield'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <input type="text" name="${field.formRelativeUniqueName}" class="date ${field.styleClass}" value="${field.value}"
          <c:if test='${field.autocomplete}'> autocomplete="${field.autocomplete}"</c:if>/>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
    <script>
      $(document).ready(function() {
        $(function() {
          $('input[class*="date"][name="${field.formRelativeUniqueName}"]').datetimepicker({
            format:'${field.dateFormat}'.replace(/m+/g,'i')
                                        .replace(/H+/g,'H')
                                        .replace(/d+/g,'d')
                                        .replace(/M+/g,'m')
                                        .replace(/y+/g,'Y'),
            step:10,
            timepicker:('${field.dateFormat}'.indexOf('HH:mm') >= 0)
          });
        });
      });
    </script>
  </c:when>

  <c:when test="${field.type eq 'radiogroup'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <ul class="radiogroup">
        <c:forEach var="radio" items="${field.fields}">
          <li>
            <input type="radio" name="${field.formRelativeUniqueName}" class="${radio.styleClass}" value="<c:out value='${radio.value}'/>"
                   <c:if test='${radio.checked}'>checked="true"</c:if> />
            <c:out value='${radio.label}'/>
          </li>
        </c:forEach>
        <c:if test="${field.allowOther}">
          <li>
            <input type="radio" name="${field.formRelativeUniqueName}" class="${field.styleClass}" value="<c:out value='${field.renderOtherValue}'/>"
              <c:if test='${field.otherValue}'>checked="true"</c:if> />
            Other:
            <span>
              <input type="text" value="<c:if test='${field.otherValue}'>${field.value}</c:if>" name="${field.otherFieldName}" class="textfield-other"
                     <c:if test='${field.length gt 0}'>size="${field.length}"</c:if> <c:if test='${field.maxLength gt 0}'>maxlength="${field.maxLength}"</c:if> />
            </span>
          </li>
        </c:if>
      </ul>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'checkboxgroup'}">
    <div class="${fieldCssClass}">
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <c:forEach var="checkbox" items="${field.fields}">
        <p>
          <input type="checkbox" name="${checkbox.formRelativeUniqueName}" class="${checkbox.styleClass}" value="<c:out value='${checkbox.value}'/>"
                 <c:if test='${checkbox.checked}'>checked="true"</c:if> />
          <c:out value='${checkbox.label}'/>
        </p>
      </c:forEach>
      <c:if test="${field.allowOther}">
        <input type="checkbox" name="${field.formRelativeUniqueName}" class="${field.styleClass}" value="<c:out value='${field.renderOtherValue}'/>"
          <c:if test='${field.otherValue}'>checked="true"</c:if> />
        Other:
        <span>
          <input type="text" value="<c:if test='${field.otherValue}'>${field.value}</c:if>" name="${field.otherFieldName}" class="textfield-other"
                 <c:if test='${field.length gt 0}'>size="${field.length}"</c:if> <c:if test='${field.maxLength gt 0}'>maxlength="${field.maxLength}"</c:if> />
        </span>
      </c:if>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'likert'}">
    <div class="${fieldCssClass}">
      <input type="hidden" name="${field.formRelativeUniqueName}"/>
      <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
      <table class="eforms-likert-table">
        <tr>
          <td>&nbsp;</td>
          <c:forEach var="option" items="${field.options}">
            <td>
              ${option}
            </td>
          </c:forEach>
        </tr>
        <c:forEach var="item" items="${field.optionsMap}">
          <tr>
            <td>${item.key.label}</td>
            <c:forEach var="radio" items="${item.value}">
              <td>
                <input type="radio" name="${radio.formRelativeUniqueName}" class="${radio.styleClass}" value="${radio.value}"
                       <c:if test='${radio.checked}'>checked="true"</c:if> />
              </td>
            </c:forEach>
          </tr>
        </c:forEach>
      </table>
      <span class="eforms-hint"><c:out value='${field.hint}'/></span>
    </div>
  </c:when>

  <c:when test="${field.type eq 'antispam'}">
    <c:choose>
      <c:when test="${field.honeyPot}">
        <div class="${fieldCssClass}"<c:if test="${empty field.extraCssClass}"> style="display:none"</c:if>>
          <label><c:out value='${field.label}'/><span class="eforms-req"><c:out value='${field.requiredMarker}'/></span></label>
          <input type="text" name="${field.formRelativeUniqueName}" class="${field.styleClass}" value="${field.value}"/>
        </div>
      </c:when>
      <c:when test="${field.slider}">
        <div class="${fieldCssClass}">
          <div id="slider"></div>
          <div id="notSlided" style="display:block">
            <p>Slide to be able to submit</p>
          </div>
          <div id="slided" style="display:none">
            <p>You may submit the form</p>
          </div>
        </div>
      </c:when>
      <c:otherwise>
        ${field.antiSpamType}
      </c:otherwise>
    </c:choose>
  </c:when>
</c:choose>
