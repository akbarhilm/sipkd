<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Cetak Jurnal Umum SKPD By Akun</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Cetak Jurnal Umum SKPD By Akun</div>       
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
                <label class="col-md-3 control-label">Tanggal Posting :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text"  path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                            <errors path="tglPosting" class="error" />
                        </div>
                    </div>
            </div>     
                       
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>           

            <div class="form-group">
                <label class="col-md-3 control-label">Akun :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idbaspop" id="idbaspop" type="hidden" value="${idbaspop}" />
                    <input path="akunketpop" id="akunketpop"  size="75" type="text" readonly='true' value="${akunketpop}"/> 
                    <a id="akunpilih"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                                  
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    
                    <button type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak </button>
                    
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/skpdbyakun.js"></script>  

