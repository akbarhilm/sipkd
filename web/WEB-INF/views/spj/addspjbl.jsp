<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spj/indexspj">Daftar SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Pengajuan SPJ Belanja Langsung</a></li>
</ul>



<form:form  method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan SPJ Belanja Langsung</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="idSpj" id='idSpj'    />
                    <input type="hidden" id="isadd" name="isadd"  />
                     <input type="hidden" id="adanilaispj" name="adanilaispj" value="${adanilaispj}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                        <c:if test="${isall ==1}"  >&nbsp;  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                            <form:errors path="skpd.idSkpd" class="label label-important" /></c:if>
                        </div>
                    </div>
                </div>     
                <div class="form-group">
                    <label class="col-md-3 control-label">No. SPJ</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:input path="noSpj" id="noSpj" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div>      
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan SPJ:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"  cssClass="required  entrybulan ruleCekBulanSekarang"      size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                    <label class="label label-important" style="color: #810505">${pesan}</label>

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Bendahara :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="bendahara.nama" id="bendahara" cssClass="required"   size="55"   readonly='true'  /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SPJ NIHIL :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="nihil" id="spjnihil">
                            <form:option value="0">ADA SPJ</form:option>
                            <form:option value="1">NIHIL</form:option>
                            <form:option value="2">NIHIL - NIHIL</form:option>
                        </form:select>
                        <form:errors path="nihil"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="ketSpj" id="ketSpj"  size="90" maxlength="400"   />  
                        <form:errors path="ketSpj"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Ubah':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/spj/indexspj">Kembali</a>
                </div>
            </div>

        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/addspjbl.js"></script>  