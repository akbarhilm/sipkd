<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    
    function ambilidskpd() {
        var id = $("#idskpdpop").val();
        window.localStorage.setItem("idskpdval",id);
        
        var bln = $("#bulan").val();
        window.localStorage.setItem("bulandval",bln);
        
    }
    
</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">BKU PENGELUARAN</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Laporan Dan Cetak BKU Pengeluaran</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanbkukeluar/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Dan Cetak BKU Pengeluaran</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodegrup" name="kodegrup" value="${pengguna.kodeGrup}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idskpdpop" id="idskpdpop" type="hidden" value="${skpdpop}" onchange="setskpd()" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan BKU :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenislaporan" name="jenislaporan" onchange="setpanel(this.value)">
                            <option value="1" selected>1 - BKU Pengeluaran</option> 
                            <option value="2">2 - BKU Per Kegiatan</option>

                        </select>
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden"  name="tgl1"  id="tgl1"  readonly="true" size="14"  value="20150101" /> 
                        <input type="hidden"  name="tgl2"  id="tgl2"  class="m-wrap large date-picker entrytanggal1"  size="14"  value="${tgl}" /> 

                        <form:select  path="bulan" id="bulan" name="bulan" onchange="setTanggalAkhir()">  
                            <form:options   />
                        </form:select >
                        <form:errors path="bulan" cssClass="error"  />

                    </div>
                </div>
            </div>

            <div class="form-group">       
                <!--   <div id="hiddenDiv2" style="height:45px;width:490px;border:1px;visibility:hidden;">  --> 
                <label class="col-md-3 control-label">Kegiatan : </label>
                <div class="col-md-5">
                    <form:hidden path="idKegiatan" id='idKegiatan' onchange="setTanggalAkhir()" />
                    <form:input path="namaKeg" id="namaKeg"  cssClass="required"   type="text" size="65" maxlength="180" readonly='true'  />
                    <a id="btnpilih" class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/laporanbkukeluar/listkegiatanpopup?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a><br>
                </div>  
            </div>           

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <!--
                    <button id="btnproses" type="button" class="btn blue"  onclick='simpan()' href="#" >Proses</button> 
                    -->
                    
                    <a id="btnproses" onclick="ambilidskpd()" class="fancybox fancybox.iframe btn blue" href="${pageContext.request.contextPath}/tutupbuku/tutupbkupop?target='_top'" title="Tutup BKU Pengeluaran"  ></i>Proses Tutup BKU</a>
                    <button id="btncetak" type="button" class="btn blue"  onclick='cetakbkukeluar()' href="#" > Cetak</button>

                </div>
            </div>   

        </div>
    </div> 

    <div class="portlet box">

        <div class="portlet-body">
            <table id="bkutable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tanggal</th>
                        <th>No Bukti</th>
                        <th>Uraian</th> 
                        <th>Kode Rekening</th> 
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th>
                        <th>Saldo</th>
                    </tr>
                </thead>
                <tbody id="bkutablebody" >
                </tbody>                
            </table>
        </div>

    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkukeluar/bkukeluarprovinsi.js"></script>  

