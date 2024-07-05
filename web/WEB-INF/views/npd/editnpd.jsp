<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">NPD</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/npd/indexnpd">Daftar NPD</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Pengajuan NPD</a></li>
</ul>



<form:form  method="post" commandName="npd"  id="npd"   action="${pageContext.request.contextPath}/npd/prosesubah" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan NPD</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="banyakrinci" name="banyakrinci" size="6">
                    <form:input path="tahunAnggaran" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                       <form:hidden path="idNpd" id='idNpd' name='idNpd' />
                       <form:hidden path="skpd.idSkpd" id='idskpd' name='idskpd'  value="${skpd.idSkpd}"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'   value="${skpd.kodeSkpd}/${skpd.namaSkpd}"/>
                        <c:if test="${isall ==1}"  >&nbsp;  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                            <form:errors path="skpd.idSkpd" class="label label-important" /></c:if>
                        </div>
                    </div>
                </div>    
                <div class="form-group">
                    <label class="col-md-3 control-label">No NPD.:</label>
                    <div class="col-md-4">
                    <form:input path="noNpd" id="no" type="text" size="10" maxlength="30" readonly='true' /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl NPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tanggalNpd" path="tanggalNpd" id="tanggalNpd" class="required form-control form-control-inline input-small date-picker2 entrytanggal valid required" size="14" value=""/>
                        <form:errors path="tanggalNpd" class="error" />

                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="nipPptk" id="nipPptk"  cssClass="required"   type="text" size="55" maxlength="80" />
                        <form:errors path="nipPptk" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="namaPptk" id="namaPptk"  cssClass="required"   type="text" size="55" maxlength="80" />
                        <form:errors path="namaPptk" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-8">
                    <form:hidden path="kegiatan.idKegiatan" id="idKegiatan" name="idKegiatan" onChange="gridnpdrincikegiatan()" />
                    <form:input path="kegiatan.namaKegiatan"  cssClass="required" id="namaKegiatan" type="text" size="75" maxlength="275" readonly='true' /> &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/npd/pilihkegiatanprogram?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a>
                    <form:errors path="kegiatan.namaKegiatan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Program:</label>
                <div class="col-md-4">
                    <form:input path="program.namaProgram" id="namaProgram" type="text" size="75" maxlength="275" readonly='true' /> 
                    <form:errors path="program.namaProgram" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Ket NPD :</label>
                <div class="col-md-4">
                    <form:input path="ketNpd" id="ketNpd" type="text" size="55" maxlength="55"  /> 
                    <form:errors path="ketNpd" class="label label-important" />
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >Ubah</button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/npd/indexnpd">Kembali</a>
                </div>
            </div>

        </div>
    </div>     

    <div class="portlet" id="rincibl">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian NPD</div>  
        </div>
        <div class="portlet-body">
            <table id="kegiatantable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Anggaran</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS</th>
                        <th>Sudah NPD</th>
                        <th>Sisa NPD</th>
                        <th>Nilai NPD</th>
                        <th></th> 
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="6" style="text-align:right">Total:</th>
                        <th colspan="2">
                            <input  type='text' id="totalsisanpd"  name="totalsisanpd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                        </th>
                        <th colspan="2" >
                            <input type='text' id="totalnpd"   name="totalnpd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                        </th>
                    </tr>
                </tfoot>
                <tbody>
                </tbody> 
            </table>
        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/npd/addnpd.js"></script>  