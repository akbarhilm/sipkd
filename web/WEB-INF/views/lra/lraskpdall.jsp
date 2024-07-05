<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Proses LRA</a></li>
</ul>

<form:form   method="post" commandName="refneraca"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Proses LRA</div>       
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
                <label class="col-md-3 control-label">Jenis LRA :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenislra" onchange="setjenis(this.value)">
                            <option value="1" selected>1 - Provinsi</option> 
                            <option value="2">2 - SKPD</option>
                        </select>
                    </div>
                </div>  
            </div>    
                
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idskpdpop" id="idskpdpop" type="hidden" value="${skpdpop}" onchange="getkodestatus()"/>
                    <input path="namaskpdpop" id="namaskpdpop" type="hidden" value="${namaskpdpop}" />
                    <input path="kodeskpdpop" id="kodeskpdpop" type="hidden" value="${kodeskpdpop}" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>    
            
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" onchange="setbulan(this.value),getkodestatus()">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Jul</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
                            <option value="13">Audited</option>
                        </select>
                    </div>
                </div>  
            </div>       
                
            <div class="form-group">
                <label class="col-md-3 control-label">DPA Perubahan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="checkbox"  id="dpaperubahan" name="dpaperubahan" value="" class="checked" />   
                    </div>
                </div>
            </div>     
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='simpan()' href="#" > Proses LRA </button>
                 <!--   <button id="btncetak" type="button" class="btn blue"  onclick='cetakjurnal()' href="#" > Cetak </button>    -->
                 <!--  <button id="btncek" type="button" class="btn blue" onclick='cek()' href="#" > cek </button>  -->
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/lra/lraskpdall.js"></script>  

