<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Cetak Jurnal Umum SKPD By No Jurnal</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Cetak Jurnal Umum SKPD By No Jurnal</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">No Jurnal :</label>
                <div class="col-md-4 col-md-9">
                    <input path="nojurpop" id="nojurpop" type="text" size="25" readonly='true' value="${nojurpop}" class="m-wrap large"  />
                    <a id="pilihnojur"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/nojurnal/listnojurnal?target='_top'" title="Pilih Nomor Jurnal"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>   
                
             <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak </button>
                </div>
            </div> 
                
                <!--
            <div class="form-group">
                <label class="col-md-3 control-label">No Jurnal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <form:select  path="nojurnal" id="nojurnal" >  
                            <form:options items="${listNojur}"  itemValue="valnojur" itemLabel = "valnojur"  />
                        </form:select >
                        <a class="icon-book" onclick='cetakjurnal()' href="#" ></a>
                        <form:errors path="nojurnal" cssClass="error"  />
                    </div>
                </div>
            </div>
                -->

        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/skpdbynojur.js"></script>  

