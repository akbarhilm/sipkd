<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Laporan Belanja</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Belanja</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
               
            <div class="form-group" id="paneljenislap">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislap"  onchange="setPanelAwal(this.value)">
                            <option value="0" >--Pilih Jenis Laporan--</option> 
                            <option value="1" >1 - Laporan Belanja Provinsi</option> 
                            <option value="2" selected>2 - Laporan Belanja SKPD</option>
                        </select>
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idskpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="idskpd" class="label label-important" />
                    </div>
                </div>
            </div>  
<%--
            <div id="labelwilayah" class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="wilayah" id="wilayah" onchange="">

                        </select>
                    </div>
                </div>  
            </div>
--%>
            <div class="form-group" id="paneltgl">
                <label class="col-md-3 control-label">s/d Tanggal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text"  path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal2 valid" size="14" value="" onchange="validasiTanggal()"/>
                            <errors path="tglPosting" class="error" />
                        </div>
                    </div>
            </div>
            <%--
            <div id="labelakunbelanja" class="form-group">
                <label class="col-md-3 control-label">Akun Belanja :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="akunbelanja" name="akunbelanja">

                        </select>
                    </div>
                </div>  
            </div>

            <div id="saldoawallabel" class="form-group">
                <label class="col-md-3 control-label">Saldo Awal : </label>
                <div class="col-md-4">
                    <input name="saldo" id="saldo" type="text" size="20"  value="" /> 
                </div>
            </div>
            --%>
            <div class="form-actions fluid" id="panelcetak">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>   

        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/laporanbelanja/laporanbelanja10.js"></script>  

