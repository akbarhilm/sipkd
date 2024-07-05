<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    function ambiljenisdok() {
        var jns = $("#jenisdok").val();
        window.localStorage.setItem("jenisdokval", jns);

        var id = $("#idskpdpop").val();
        window.localStorage.setItem("idskpdval", id);

    }

</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Pencarian Buku Kas Umum</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku" action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum</div>   

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="kodebebanket" name="kodebebanket"  />
                    <input type="hidden" id="ketidspp" name="ketidspp"  />
                    <input type="hidden" id="banyaknobukti" name="banyaknobukti"  />
                    <input type="hidden" id="namaakuntext" name="namaakuntext"  />

                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idskpdpop" id="idskpdpop" type="hidden" value="${skpdpop}" onchange="cekjenisgrid()" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Pencarian :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenis" onchange="setPanel(this.value), getTanggalAwalAkhir()">
                            <option value="1" selected>No Dokumen</option> 
                            <option value="2">Sisa Uang di PPTK (Panjar)</option>
                            <option value="7">Sisa Uang per PPTK (Panjar)</option>
                            <option value="3">Kegiatan</option>
                            <option value="4">Cek Setoran Tunai</option>
                            <option value="5">Setoran ke BUD</option>
                            <option value="6">Pajak</option>

                        </select>
                    </div>
                </div>  
            </div>

            <div id="labeljenisdok" class="form-group">
                <label class="col-md-3 control-label">Jenis Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenisdok" onchange="cleardokumen()">
                            <option value="SP2D">SP2D</option> 
                            <option value="SPJ">SPJ</option>

                        </select>
                    </div>
                </div>  
            </div>

            <div id="labeljenispajak" class="form-group">
                <label class="col-md-3 control-label">Jenis Pajak :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenispajak" onchange="cekjenisgrid()">
                            <option value="PS" selected>Semua Pajak</option>
                            <option value="P1">PPH PS 21</option> 
                            <option value="P2">PPH PS 22</option>
                            <option value="P3">PPH PS 23 Jasa I</option>
                            <option value="P4">PPH PS 4 Ayat 2</option>
                            <option value="P5">PPN</option>
                            <option value="P6">PPH</option>
                        </select>
                    </div>
                </div>  
            </div>

            <div id="labelnodok" class="form-group">
                <label class="col-md-3 control-label">No Dokumen :</label>
                <div class="col-md-9">
                    <input type="text" name="noDokumen"  id="noDokumen"  class="m-wrap large" size="75" readOnly="true" onchange="cekjenisgrid()"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihNoDok" onclick="ambiljenisdok()" href="${pageContext.request.contextPath}/bkupencarian/listnodokumen11?target='_top'" title="Pilih Nomor Dokumen"  ><i class="icon-zoom-in"></i> Pilih</a> 
                </div>
            </div>

            <div id="labelpptk2" class="form-group">
                <label class="col-md-3 control-label">PPTK :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganPPTK"  id="keteranganPPTK"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihPptk" onclick="ambiljenisdok()" href="${pageContext.request.contextPath}/bkupencarian/listpptk11?target='_top'" title="Pilih PPTK"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="nipPPTK" name="nipPPTK"  onchange="cekjenisgrid()">
                    <input type="hidden" id="namaPPTK" name="namaPPTK"  >
                </div>
            </div>

            <div id="labelkegiatan" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKeg"  id="keteranganKeg"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKeg" onclick="ambiljenisdok()" href="${pageContext.request.contextPath}/bkupencarian/listkegiatan11?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatan" name="idKegiatan"  onchange="cekjenisgrid()">
                </div>
            </div>

            <div id="labelpagu" name="labelpagu" class="form-group">
                <label class="col-md-3 control-label">Nilai Anggaran :</label>
                <div class="col-md-4">
                    <input name="nilaianggaran" id="nilaianggaran" type="text" size="20" readonly="true"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Awal:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tglAwal" class="required date-picker2 entrytanggal valid" size="14" value="" onchange="getTanggalAwalAkhir(), cekjenisgrid()" />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Akhir :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tglAkhir" class="required date-picker2 entrytanggal valid" size="14" value="" onchange="getTanggalAwalAkhir(), cekjenisgrid()" />
                    </div>
                </div>  
            </div>
        </div> 
    </div> 


    <div id="mygrid" class="portlet box">

        <div id="standardgrid" class="portlet-body">

            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No Dokumen</th>
                        <th>Tgl Transaksi</th>
                        <th>No BKU</th>
                        <th>No Filling</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th> 
                        <th>Saldo Kas</th>  
                    </tr>
                </thead>

            </table>
        </div>

        <div id="kegiatangrid" class="portlet-body">

            <table id="kegiatantable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No Dokumen</th>
                        <th>Kode Akun</th>
                        <th>Tgl Transaksi</th>
                        <th>No BKU</th>
                        <th>No Filling</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th> 
                        <th>Saldo Kas</th>  
                    </tr>
                </thead>

            </table>
        </div>

        <div id="nodokgrid" class="portlet-body">

            <table id="nodoktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Akun</th>
                        <th>Tgl Transaksi</th>
                        <th>No BKU</th>
                        <th>No Filling</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th> 
                        <th>Saldo Kas</th> 
                    </tr>
                </thead>

            </table>
        </div>

        <div id="pptk2grid" class="portlet-body">

            <table id="pptk2table" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No Dokumen</th>
                        <th>Tgl Transaksi</th>
                        <th>No BKU</th>
                        <th>No Filling</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th> 
                        <th>Saldo Kas</th>  

                    </tr>
                </thead>

            </table>
        </div>

        <div id="allpajakgrid" class="portlet-body">

            <table id="allpajaktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No Dokumen</th>
                        <th>Jenis</th>
                        <th>Tgl Transaksi</th>
                        <th>No BKU</th>
                        <th>No Filling</th>
                        <th>Penerimaan</th>
                        <th>Pengeluaran</th> 
                        <th>Saldo Kas</th>  
                    </tr>
                </thead>

            </table>
        </div>

        <div id="pptkgrid" class="portlet-body">

            <table id="pptktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>

                    <tr>
                        <th rowspan="2" style="	vertical-align: middle;">No</th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="nipfilter" onkeyup="cari()"/></th>
                        <th><input type="text"   style=" border:none;margin:0;width:98%;" id="namafilter" onkeyup="cari()" /></th>
                        <th rowspan="2" style=" vertical-align: middle;" >No Dokumen</th> 
                        <th rowspan="2" style=" vertical-align: middle;" >Tgl Transaksi</th> 
                        <th rowspan="2" style="	vertical-align: middle;">No BKU</th>
                        <th rowspan="2" style="	vertical-align: middle;">No Filling</th>
                        <th rowspan="2" style="	vertical-align: middle;" >Penerimaan</th>
                        <th rowspan="2" style="	vertical-align: middle;">Pengeluaran</th>
                        <th rowspan="2" style="	vertical-align: middle;">Saldo Kas</th>

                    </tr>
                    <tr>

                        <th style="width: 8%">NIP PPKK</th>
                        <th>Nama PPTK</th>

                    </tr>

                </thead>

            </table>
        </div>

    </div>

    <!--
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <a class="btn blue"  href="${pageContext.request.contextPath}/beranda" >Kembali</a>
        </div>
    </div> 
    -->

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkupencarian/bkupencarian11.js"></script>  

