<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });

    function ambilketerangan() {
         
        ketcombo = "SPJ";
       
        window.localStorage.setItem("keteranganKeg", ketcombo);
        window.localStorage.setItem("ketCaraBayar", $("#kodePembayaran").val());
        window.localStorage.setItem("ketBeban", $("#beban").val());
   
    }

</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bku2016/indexbku" >Buku Kas Umum</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Edit Buku Kas Umum</a></li>

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
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="" /> 
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
                <label class="col-md-3 control-label">No BKU :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="noBKU" id='noBKU' type="text" readonly="true"  size="16" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="bulan" id='bulan' onchange="">
                            <form:option value="01">01 - Januari</form:option> 
                            <form:option value="02">02 - Februari</form:option>
                            <form:option value="03">03 - Maret</form:option>
                            <form:option value="04">04 - April</form:option>
                            <form:option value="05">05 - Mei</form:option>
                            <form:option value="06">06 - Juni</form:option>
                            <form:option value="07">07 - Juli</form:option>
                            <form:option value="08">08 - Agustus</form:option>
                            <form:option value="09">09 - September</form:option>
                            <form:option value="10">10 - Oktober</form:option>
                            <form:option value="11">11 - November</form:option>
                            <form:option value="12">12 - Desember</form:option>
                        </form:select>

                        <form:errors path="bulan" class="error" />

                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:select path="kodeTransaksi" id="kodeTransaksi" onchange="getSisaPajak()" >
                            <form:option value="P1">PPH PS 21 ( Penerimaan / Penyetoran  Pajak)</form:option>
                            <form:option value="P2">PPH PS 22 ( Penerimaan / Penyetoran  Pajak)</form:option>
                            <form:option value="P3">PPH PS 23 JASA I ( Penerimaan / Penyetoran  Pajak)</form:option>
                            <form:option value="P4">PPH PS 4 Ayat 2 ( Penerimaan / Penyetoran  Pajak)</form:option>
                            <form:option value="P5">PPN ( Penerimaan / Penyetoran  Pajak)</form:option>
                            <form:option value="P6">PPH Pasal 26 - Hadiah Undian ( Penerimaan / Penyetoran  Pajak)</form:option>
                            
                        </form:select>

                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglPosting" id="tglPosting"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:hidden path="noJournalDok" id='noJournalDok'  />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-4">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text"  size="55" /> 
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                        <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                        <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                        <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                        <input type="hidden" id="ketKegpop" name="ketKegpop"  onchange="" value="">
                        <input type="hidden" id="banyakrinci" name="banyakrinci"  onchange="" value="">

                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <div class="input-group" id="">
                        <form:input path="inboxFile" id="inboxFile" type="text"  class="required "  size="14" maxlength="10" /> 
                        <form:errors path="inboxFile" class="error" />

                    </div>
                </div>  
            </div>
                        
            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="kodePembayaran" id="kodePembayaran" >
                            <form:option value="1">1 - Tunai</form:option> 
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                        </form:select>
                    </div>
                </div>  
            </div>
            
            <div id="labelkegiatanpajak" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <form:input type="text" path="ketKegiatan"  id="ketKegiatan"  class="m-wrap large" size="80" readOnly="true"/>  
                    <!-- &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegPajak" onclick="ambilketerangan()" href="${pageContext.request.contextPath}/bku2016/listkegiatan?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> -->
                    <form:hidden path="idKegiatan" id='idKegiatan'  />
                    <form:hidden id="kodeKeg" path="kodeKeg"  value="" />
                </div>
            </div>
                
            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisPembayaran" id="jenisPembayaran" onchange="jenisBayar()" >
                            <option value="PN">Penerimaan</option> 
                            <option value="PG">Pengeluaran</option>
                            
                        </select>
                    </div>
                </div>  
            </div>
                 
            <div id="labelbeban" name="labelbeban"  class="form-group">
                <label class="col-md-3 control-label">Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select id="beban" path="beban" onchange="" >
                            <form:option value="UP" >UP/GU</form:option> 
                            <form:option value="TU">TU</form:option>
                            <form:option value="LS">LS</form:option>
                        </form:select>
                        <form:errors path="beban" class="error" />
                    </div>
                </div>  
            </div>
                
            <div id="labeljenis" name="labeljenis"  class="form-group">
                <label class="col-md-3 control-label">Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select id="jenis" path="jenis" onchange="clearrow(), setakunpajak()" disabled="true">
                            <form:option value="1" >BTL</form:option>
                            <form:option value="2" >BTL BANTUAN</form:option> 
                            <form:option value="3" >BL</form:option> 
                            <form:option value="4" >BIAYA</form:option> 
                        </form:select>
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

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" ROWS="3" maxlength="400" />
                </div>
            </div>
            
        </div>
    </div>  

    <div class="portlet box">
        <div class="portlet-body">
            <table id="jourtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
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
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!--  <button type="button" id="btncek" class="btn blue" onclick='CEK()' >CEK</button> -->  

            <button type="button" id="btnTambah" class="btn blue" onclick='cektambah()' >Tambah Data</button>
            <button type="button" class="btn blue" onclick='cekLengkap()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku2016/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku2016/editbkupajak.js"></script>  

