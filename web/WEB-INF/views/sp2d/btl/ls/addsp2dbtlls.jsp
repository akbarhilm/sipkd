<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/sp2dbtlls/indexsp2dbtlls/${refsp2d.skpd.idSkpd}">Daftar SP2D BTL LS</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Pengajuan SP2D LS Belanja Tidak Langsung</a></li>
</ul>



<form:form  method="post" commandName="refsp2d"  id="refsp2d"   action="${pageContext.request.contextPath}/sp2dbtlls/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan SP2D Belanja Tidak Langsung LS</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="kodeWilayah" id="kodewilayah" name="kodewilayah" value="${sessionScope.pengguna.kodeProses}"  /> 
                    <form:hidden path="id" id='idsp2d'    />
                    <form:hidden path="idSpp" id='idSpp'    />
                    <form:hidden path="idspm" id='idSpm'    />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'  value="${skpd.idSkpd}"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                    </div>
                </div>
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">No/Tgl SPM</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="noSpm" id="noSpm" type="text" size="12" maxlength="20" readonly='true'  /> / <form:input type="text" size="15" name="tglSpm" path="tglSpm" id="tglSpm" readonly="true"/>
                        <form:errors path="noSpm" class="label label-important" />  <form:errors path="tglSpm" class="label label-important" />
                    </div>
                </div>
            </div>      
            <div class="form-group">
                <label class="col-md-3 control-label">No. SP2D</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSp2d" id="noSp2d" type="text" size="12" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tgl SP2D :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tanggalSp2d" path="tanggalSp2d" id="tanggalSp2d" readonly="true" /> </div>
                    <input type='hidden' name='sp2dtanggal' id='sp2dtanggal' value="${tglsp2d}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP KBUD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type='hidden' name='kodeDokTT' id='kodeDokTT'>
                        <form:input  cssClass="required" path="nipKbud" id="nipKbud" readOnly="true" size="30" maxlength="18"   />  &nbsp; <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/sp2dbtlls/listkbud?target='_top'" title="Pilih KBUD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        <form:errors path="nipKbud"  cssClass="error"  />
                        <form:hidden  cssClass="required" path="nipPenggunaAnggaran" id="nipPenggunaAnggaran" readOnly="true" size="30" maxlength="50"   /> 
                        <form:hidden cssClass="required" path="namaPenggunaAnggaran" id="namaPenggunaAnggaran" readOnly="true" size="30" maxlength="80"   />  
                        <form:hidden cssClass="required" path="nrkPenggunaAnggaran" id="nrkPenggunaAnggaran" readOnly="true" size="30" maxlength="50"   /> 
                        
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama KBUD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaKbud" id="namaKbud" readOnly="true" size="30" maxlength="80"   />  
                        <form:errors path="namaKbud"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">JABATAN KBUD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="jabatanKbud" id="jabatanKbud" readOnly="true"  size="30" maxlength="80"   />  
                        <form:errors path="jabatanKbud"  cssClass="error"  />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >Simpan</button>
                    <a class="btn blue"   href="${pageContext.request.contextPath}/sp2dbtlls/indexsp2dbtlls/${refsp2d.skpd.idSkpd}">Kembali</a>
                </div>
            </div>

        </div>
    </div>   

    <div class="portlet" id="rincibl">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian SP2D BTL</div>  
        </div>
        <div class="portlet-body">
            <table id="btllssp2drincitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SP2D</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>     
                <tfoot>
                    <tr>
                        <th colspan="3" style="text-align:right">Total:</th>
                        <th >
                            <form:input path='nilaiSp2d'  type='text' id="totalsp2d"  name="totalsp2d" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                        </th>

                    </tr>
                </tfoot>
            </table>
        </div>
    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/btlls/addsp2dbtlls.js"></script>


