<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Setor</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar Setor</a> 
        <span class="icon-angle-right"></span> 
    </li>
    <li><a href="#">Tambah  Setor</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/setor/simpansetorupgu" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form Setor</div>       
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                     <form:hidden path="tahunSetor" id="tahunSetor" value="${tahunAnggaran}" /> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="90"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">No Setor:</label>
                <div class="col-md-5">
                    <div class="input-group">                      
                        <form:input path="noSetor" type="text"  id="noSetor" readonly="true" class="m-wrap large" size="20"    />
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="14" value=""/>
                        <form:errors path="tglSetor" class="error" />
                    </div>
                </div>  
            </div>
                                        
            <div class="form-group">
                <label class="col-md-3 control-label">BEBAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="beban" >
                           
                            <option value="UP">UP/GU</option> 
							 <option value="TU">TU</option> 
                        </form:select>
                        <form:errors path="beban" class="error" />
                    </div>
                </div>  
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai:</label>
                <div class="col-md-5">
                    <div class="input-group">                        
                        <form:input path="nilaiSetor" type="text" id="nilaiSetor"  class="m-wrap large" size="20"  placeholder="x.xx.xx.xxx"  />
                    </div>
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/setor/indexupgu" >Kembali</a>
                </div>
            </div>

        </div>
    </div>

</form:form>
 <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spjsetor/addsetorupgu.js"></script>    

