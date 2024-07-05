<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {
        var id_value = window.localStorage.getItem("indexbkubulan");
        bulanval = id_value;
        setBulan();
        //console.log("bulan value == "+bulanval);
    });


    function ambilketerangan() {
        var jenistrans = $('#jenisTransaksi').val();

        if (jenistrans.substr(0, 1) == "P") {
            ketcombo = "SPJ";
        }
        
        //console.log("ketcombo = "+ketcombo);
        
        window.localStorage.setItem("keteranganKeg", ketcombo);
        window.localStorage.setItem("ketCaraBayar", $("#kodePembayaran").val());
        window.localStorage.setItem("ketBeban", $("#beban").val());
    }

    function ketsaldoawal() {
        window.localStorage.setItem("ketSaldoAwal", ketSaldoAwal);
    }


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bku2016/indexbku" >Buku Kas Umum - Pengeluaran</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Buku Kas Umum</a></li>

</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
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
                    <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />

                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                </div>
            </div>

            <div id="labelwilayah" class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input name="wilayah" id="wilayah" type="text" readonly="true" />
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:hidden path="skpd.kodeSkpd" id='kodeskpd' value="${skpd.kodeSkpd}"  />
                        <form:hidden path="skpd.namaSkpd" id='namaskpd' value="${skpd.namaSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan" onchange="keteranganBulan()">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Juli</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
                        </select>
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenisTransaksi" id="jenisTransaksi" onchange="setJenis();
                                setGrid();
                                getBendahara();
                                setBeban();
                                gantiText(this.value);
                                setTanggalEnable()">
                            <option value="JO" selected>1 - SP2D</option> 
                            <option value="JJ">2 - SPJ</option>
                            <option value="-"> PAJAK -------------------------------------------------------------------------------</option>
                            <option value="P1">3.1 - PPH PS 21</option>
                            <option value="P2">3.2 - PPH PS 22</option>
                            <option value="P3">3.3 - PPH PS 23 JASA I</option>
                            <option value="P4">3.4 - PPH PS 4 Ayat 2</option>
                            <option value="P5">3.5 - PPN</option>
                            <option value="P6">3.6 - PPH</option>
                            <option value="-">SETORAN ---------------------------------------------------------------------------</option>
                            <option value="ST">4 - SETORAN KE PPKD</option>
                            <option value="-">CEK ------------------------------------------------------------------------------------</option>
                            <option value="C1">5 - Pencairan Cek dari Rekening Bendahara Pengeluaran SKPD</option>
                            <option value="C2">6 - Setor ke Rekening Bendahara Pengeluaran SKPD</option>
                            <option value="-">NPD ------------------------------------------------------------------------------------</option>
                            <option value="NP">7 - NPD KE PPTK/dll</option>
                            <option value="NM">8 - PPTK/dll KE NPD</option>
                            <option value="-">--------------------------------------------------------------------------------------------</option>
                            <option value="LL">9 - Lain-lain</option>
                        </select>
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" value="" />
                        <input type="hidden" id="tglHide" class="required date-picker2 entrytanggal " value=""/>
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-4">
                    <div class="input-group" id="nobuktiketerangan">
                        <select path="noBuktiDok" id="noBuktiDok" onchange="gantinobukti()" >
                            <option </option> 
                        </select>
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" path="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                        <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                        <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                        <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                        <input type="hidden" id="ketKegpop" name="ketKegpop"  onchange="" value="">
                        <input type="hidden" id="idspdKegpop" name="idspdKegpop"  onchange="" value="">
                        <input type="hidden" id="bebanSpjpop" name="bebanSpjpop"  onchange="" value="">
                    </div>
                </div>  
            </div>

            <div id="labelfileinbox" name="labelfileinbox" class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <input name="fileInbox" id="fileInbox" type="text" maxlength="10"/>
                </div>
            </div>

            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Jenis Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisPembayaran" id="jenisPembayaran" onchange="clearrow()" >
                            <option value="PN">Penerimaan</option> 
                            <option value="PG">Pengeluaran</option>

                        </select>
                    </div>
                </div>  
            </div>

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="kodePembayaran" id="kodePembayaran" onchange="cekPanjar(this.value)" >
                            <option value="1">1 - Tunai</option> 
                            <option value="2">2 - Bank/Transfer/Cek</option>  
                        </select>
                    </div>
                </div> 
            </div>

            <div id="labelakunlainlain" class="form-group">
                <label class="col-md-3 control-label">Akun :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganAkunLain"  id="keteranganAkunLain"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihAkunLain" onclick="ketsaldoawal()" href="${pageContext.request.contextPath}/bku2016/listakun?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idbaslain" name="idbaslain"  value="">
                    <input type="hidden" id="kodeAkunLain" name="kodeAkunLain"  value="">
                </div>
            </div>

            <div id="labelkegiatanspj" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPop"  id="keteranganKegPop"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegSpj" onclick="ambilketerangan()" href="${pageContext.request.contextPath}/bku2016/listkegiatan?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanSpj" name="idKegiatanSpj"  value="">
                    <input type="hidden" id="kodeKegSpj" name="kodeKegSpj"  value="">
                </div>
            </div>

            <div id="labelkegiatannm" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPopNm"  id="keteranganKegPopNm"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegNM"  href="${pageContext.request.contextPath}/bku2016/listkegiatannm?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanNm" name="idKegiatanNm"  value="">
                    <input type="hidden" id="kodeKegNm" name="kodeKegNm"  value="">
                </div>
            </div>

            <div id="labelkegiatannp" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPopNp"  id="keteranganKegPopNp"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegNP"  href="${pageContext.request.contextPath}/bku2016/listkegiatannp?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanNp" name="idKegiatanNp"  value="" >
                    <input type="hidden" id="kodeKegNp" name="kodeKegNp"  value="">
                </div>
            </div>

            <div id="labelkegiatanpajak" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPopPj"  id="keteranganKegPopPj"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegPajak" onclick="ambilketerangan()" href="${pageContext.request.contextPath}/bku2016/listkegiatan?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanPajak" name="idKegiatanPajak"  value="" >
                    <input type="hidden" id="kodeKegPajak" name="kodeKegPajak"  value="">
                </div>
            </div>

            <div id="labelbeban" name="labelbeban"  class="form-group">
                <label class="col-md-3 control-label">Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="beban" name="beban" onchange="setPengeluaran()">

                        </select>
                    </div>
                </div>  
            </div>

            <div id="labelsisaspj" name="labelsisaspj" class="form-group">
                <label id="textlabelsisaspj" class="col-md-3 control-label">Sisa Uang Persediaan (Kas SKPD) :</label>
                <div class="col-md-4">
                    <input name="sisaspj" id="sisaspj" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelnilaispd" name="labelnilaispd" class="form-group">
                <label class="col-md-3 control-label">Nilai SPD (1) :</label>
                <div class="col-md-4">
                    <input name="nilaispd" id="nilaispd" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelnilaikontrak" name="labelnilaikontrak" class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak (2) :</label>
                <div class="col-md-4">
                    <input name="nilaikontrak" id="nilaikontrak" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelnilaibku" name="labelnilaibku" class="form-group">
                <label class="col-md-3 control-label">Nilai BKU UP/GU (3) :</label>
                <div class="col-md-4">
                    <input name="nilaibku" id="nilaibku" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelnilaispptu" name="labelnilaikontrak" class="form-group">
                <label id="txtlabelnilaitu" class="col-md-3 control-label">Nilai SPP TU (4) :</label>
                <div class="col-md-4">
                    <input name="nilaispptu" id="nilaispptu" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelnilaisetorantu" name="labelnilaibku" class="form-group">
                <label class="col-md-3 control-label">Nilai Setoran TU (5) :</label>
                <div class="col-md-4">
                    <input name="nilaisetorantu" id="nilaisetorantu" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labelsisaspd" name="labelsisaspd" class="form-group">
                <label class="col-md-3 control-label">Total Sisa SPD (1-2-3-4+5) :</label>
                <div class="col-md-4">
                    <input name="sisaspd" id="sisaspd" type="text" readonly="true" style="text-align: right"/>
                </div>
            </div>

            <div id="labeljenis" name="labeljenis"  class="form-group">
                <label class="col-md-3 control-label">Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="jenis" name="jenis" onchange="setColumn(this.value)">

                        </select>
                    </div>
                </div>  
            </div>

            <div id="labelsisapajak" name="labelsisapajak"  class="form-group">
                <label class="col-md-3 control-label">Sisa Pajak :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name="sisapajak" id="sisapajak" type="text" readonly="true"/>
                    </div>
                </div>  
            </div>

            
            <div id="labelpengeluaran" name="labelpengeluaran" class="form-group">
                <label id="labelpengeluarantext" class="col-md-3 control-label">Nilai Pengeluaran :</label>
                <div class="col-md-4">
                    <input name="pengeluaran" id="pengeluaran" type="text" onchange="setformatpengeluaran(this.value)"/>
                </div>
            </div>

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" />
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" />
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <!-- <input name="uraian" id="uraian" type="text" size="50" /> -->
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>
                    
            <div id="labeluraianterima" name="labeluraianterima" class="form-group">
                <label class="col-md-3 control-label">Uraian (Penerimaan):</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraianterima" id="uraianterima" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>
                    
            <div id="labeluraiankeluar" name="labeluraiankeluar" class="form-group">
                <label class="col-md-3 control-label">Uraian (Pengeluaran):</label>
                <div class="col-md-4">
                    <TEXTAREA name="uraiankeluar" id="uraiankeluar" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>

        </div>
    </div>  

    <div id="mygrid" class="portlet box">

        <div id="tabelDLL" class="portlet-body">
            <table id="jourtable2" class="table table-striped table-bordered table-condensed table-hover" >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th>Penerimaan</th> 
                        <th>Pengeluaran</th> 
                    </tr>
                </thead>

                <tbody id="jourtablebody2" >
                    <tr>

                    </tr>
                </tbody> 

            </table>
        </div>

        <div id="tabelSP2D" class="portlet-body">

            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th>Penerimaan</th> 
                        <th>Pengeluaran</th> 
                    </tr>
                </thead>

                <tbody id="jourtablebody" >
                    <tr>

                    </tr>
                </tbody> 

            </table>

        </div>

        <div id="tabelSPJ" class="portlet-body">

            <table id="spjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS (BAST)</th>
                        <th>Nilai SPJ Sebelum</th> 
                        <th>Sisa SPD</th>
                        <th>Nilai SPJ</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="spjtablebody" >
                    <tr>

                    </tr>
                </tbody> 
                
                <tfoot>
                    <tr>
                        <th colspan="7" style="text-align:right">Total:</th>
                       
                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                
            </table>
        </div>
        
        <div id="tabelNM" class="portlet-body">

            <table id="nmtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Nilai NPD</th>
                        <th>Nilai PPTK ke NPD Sebelum</th>
                        <th>Sisa PPTK ke NPD</th>
                        <th>Nilai PPTK ke NPD</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="nmtablebody" >
                    <tr>

                    </tr>
                </tbody> 

            </table>
        </div>
        
        <div id="tabelNP" class="portlet-body">

            <table id="nptable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Nilai SPD</th>
                        <th>SP2D-LS</th>
                        <th>NPD Sebelum</th>
                        <th>Pengembalian NPD</th>
                        <th>Nilai SPJ</th> 
                        <th>Sisa NPD</th>
                        <th>Nilai NPD</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="nptablebody" >
                    <tr>

                    </tr>
                </tbody> 

            </table>
        </div>

    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!-- <button type="button" id="" class="btn blue" onclick='cekcek()' >Cek</button>  -->
            
            <button type="button" id="btnTambah" class="btn blue" onclick='cektambah()' >Tambah Data</button>
            <button type="button" id="btnHapus" class="btn blue" onclick='deleteendrow()' >Hapus Data</button>  
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku2016/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku2016/addbku.js"></script>  

