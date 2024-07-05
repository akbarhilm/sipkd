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
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosesubah" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan SPJ Belanja Langsung</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="idSpj" id='idspj'    />
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="adanilaispj" name="adanilaispj" value="${adanilaispj}"  />
                    <input type="hidden" id="tanda" path="tanda" name="tanda"  onchange="gridspjrinci()" value="0"/>
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'  />
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
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan ruleCekBulanSekarang"      size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                    <label class="label label-important" style="color: #810505">${pesanbulan}</label>
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
                        <form:select path="nihil" id="nihiloption">
                            <form:option value="0">ADA SPJ</form:option>
                            <form:option value="1">NIHIL</form:option>
                            <form:option value="2">NIHIL - NIHIL</form:option>
                        </form:select>
                        <input type="text" id="untuknihil" readonly="true" size="14">
                        <form:errors path="nihil"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketSpj" id="ketSpj"  cssClass="required" onkeyup="gridspjrinci()"   size="90" maxlength="400"   />  
                        <form:errors path="ketSpj"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="submit" class="btn blue" >Ubah</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/spj/indexspj">Kembali</a>
                </div>
            </div>

        </div>
    </div>

    <div class="portlet" id="rincibl">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian SPJ BL</div>  
        </div>
        <div  style='float: right'>       &nbsp;<a class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/spj/tambahkegiatan/${refsppup.idSpj}?target='_top" title="Tambah Kegiatan"><i class="icon-zoom-in"></i> Tambah Kegiatan</a>
        </div>
        <div class="portlet-body">
            <table id="spjbltable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th>Beban</th>
                        <th>SPJ Sebelumnya</th>
                        <th>SPJ Saat Ini</th>
                        <th>Edit/Hapus</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>                
            </table>
        </div>
    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/addspjbl.js"></script>  