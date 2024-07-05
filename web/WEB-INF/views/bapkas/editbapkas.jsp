<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
    $(document).ready(function() {
        setBulan($('#tahun').val(), $('#idskpd').val());
        setBulanEdit($('#tahun').val(), $('#idskpd').val());
        
        /*var mm = $('#tglBkuBa').val().substr(4, 2);
        var dd = $('#tglBkuBa').val().substr(6, 2);
        var yyyy = $('#tglBkuBa').val().substr(0, 4);

        var tgl = dd + "/" + mm + "/" + yyyy;
        */
         var mm = $('#tglBkuBaformat').val().substr(4, 2);
        var dd = $('#tglBkuBaformat').val().substr(6, 2);
        var yyyy = $('#tglBkuBaformat').val().substr(0, 4);

        var tgl = dd + "/" + mm + "/" + yyyy;
        $('#tglBkuBa').val(tgl);
        
        
        //$('#tglBkuBa').val(tgl);
/*
        var nilaiUangKertas1 = accounting.formatNumber($("#nilaiUangKertas1").val(), 2, '.', ",");
        $('#nilaiUangKertas1').val(nilaiUangKertas1);

        var nilaiUangLogam1 = accounting.formatNumber($("#nilaiUangLogam1").val(), 2, '.', ",");
        $('#nilaiUangLogam1').val(nilaiUangLogam1);

        var nilaiUangSp2d1 = accounting.formatNumber($("#nilaiUangSp2d1").val(), 2, '.', ",");
        $('#nilaiUangSp2d1').val(nilaiUangSp2d1);

        var nilaiUangSaldoBank1 = accounting.formatNumber($("#nilaiUangSaldoBank1").val(), 2, '.', ",");
        $('#nilaiUangSaldoBank1').val(nilaiUangSaldoBank1);

        var nilaiUangSuratBerharga1 = accounting.formatNumber($("#nilaiUangSuratBerharga1").val(), 2, '.', ",");
        $('#nilaiUangSuratBerharga1').val(nilaiUangSuratBerharga1);

        var nilaiUangSaldoBkuBa = accounting.formatNumber($("#nilaiUangSaldoBkuBa").val(), 2, '.', ",");
        $('#nilaiUangSaldoBkuBa').val(nilaiUangSaldoBkuBa);
        
        var valUangSelisihBkuBa = accounting.formatNumber($("#nilaiUangSelisihBkuBa").val(), 2, '.', ",");
        $('#nilaiUangSelisihBkuBa').val(valUangSelisihBkuBa);
        */
       
    });
</script>

<style>
    .inputmoneyX{
        border:none;margin:0;text-align:right;width:80%;background: aliceblue;
    }
</style>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bapkas/indexbapkas">Daftar Berita Acara Pemeriksaan (BAP) Kas</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Berita Acara Pemeriksaan (BAP) Kas</a></li>
</ul>


<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/bapkas/prosesubah2" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Berita Acara Pemerikasaan (BAP) Kas</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="idSkpdBAPKas" id="idSkpdBAPKas" size="11"  /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   value="${skpd.idSkpd}"  />
                        <form:input path="skpd" cssClass="required" id="skpd"   type="text" size="100" readonly='true' value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />
                        <form:hidden path="namaHari" id="namaHari" size="10"  /> 
                    </div>
                </div>
            </div>     
            <div id="hiddenDiv1" style="visibility:hidden;" class="form-group">
                <label class="col-md-3 control-label">Kode Wilayah SP2D</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="kodeWilSp2d">
                            <option value="0">0 - Provinsi</option>
                            <option value="1">1 - Jakarta Pusat</option>
                            <option value="2">2 - Jakarta Utara</option>
                            <option value="3">3 - Jakarta Barat</option>
                            <option value="4">4 - Jakarta Selatan</option>
                            <option value="5">5 - Jakarta Timur</option>
                            <option value="6">6 - Kepulauan Seribu</option>
                            <option value="7">7 - Balai Kota</option>
                        </form:select>
                    </div>
                </div>
            </div>       

            <!-- <div class="form-group">
                 <label class="col-md-3 control-label">Nama Hari :</label>
                 <div class="col-md-4">
                     <div class="input-group">
            <form:input path="namaHari" id="namaHari" cssClass="required"   size="10"  /> 
        </div>
    </div>
</div>-->

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="tglBkuBa" id="tglBkuBa"  class="required  date-picker2 entrytanggal2 valid"  size="14" />  
                        <form:hidden path="tglBkuBaformat" id='tglBkuBaformat' /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input type="text"  path="blnBkuBa" id="blnBkuBa1" size="15" disabled="true"   />  



                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkPa" id="nrkPa"  size="7" maxlength="6" onKeyPress="return numbersonly(this, event)"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipPa" id="nipPa"  size="20" maxlength="18"  onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaPa" id="namaPa"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanPa" id="jabatanPa"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkBend" id="nrkBend"  size="7" maxlength="6"  onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipBend" id="nipBend"  size="20" maxlength="18"  onKeyPress="return numbersonly(this, event)" />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaBend" id="namaBend"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanBend" id="jabatanBend"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="noSkGub" id="noSkGub"  size="50" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="tglSkGub" id="tglSkGub"  size="20" maxlength="18"   />
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Kertas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangKertas"  id="nilaiUangKertas1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatKertas(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(1)
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Logam:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangLogam"  id="nilaiUangLogam1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatLogam(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(2)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSp2d"  id="nilaiUangSp2d1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatSp2d(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(3)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBank"  id="nilaiUangSaldoBank1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  onchange="setformatSaldoBank(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(4)  

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Surat Berharga:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSuratBerharga"  id="nilaiUangSuratBerharga1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" onchange="setformatSuratBerharga(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right"  />&nbsp;&nbsp;&nbsp;(5) 

                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Total BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangTotalBkuBa"   id="nilaiUangTotalBkuBa1" size="19" readonly="true"  style="text-align:right" />(6=1+2+3+4+5)

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBkuBa"  id="nilaiUangSaldoBkuBa"  onkeyup="getNilaiTotalSelisihBAPKas(),cektotalbk()" onchange="setformatSaldoBkuBa(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right" readonly="true"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Selisih BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSelisihBkuBa"  id="nilaiUangSelisihBkuBa" onchange="setformatSaldoBkuBa(this.value)"   size="19" onkeypress="return isNumber(event)" style="text-align:right" readonly="true"  />  
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Penjelasan Perbedaan positif/negatif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea  cols="70" rows="3"   path="ketBkuBa" id="ketBkuBa"   />
                    </div>
                </div>
            </div>        

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >Ubah</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/bapkas/indexbapkas">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!--<button type="button" class="btn red" onclick='cetakjurnal()' href="#" > Cetak</button>-->
            <!--<button type="button" class="btn blue" id='btnSimpan' onclick='simpan()'>Simpan</button>-->
            <button type="button" class="btn blue" id="tambah" onclick='tambahRow()' >Tambah Data</button>
            <button type="button" class="btn dark-grey" onclick='gridbapkasrinci()' >Clear</button> 
            <!--<a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a> -->
        </div>
    </div>
    <div class="portlet box red">
        <form id="formasettetap">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>Rincian Berita Acara Pemerikasaan (BAP) Kas</div>       
            </div>
            <div class="portlet-body">

                <input type="hidden" id="banyakrinci" name="banyakrinci"  value="banyakrinci"/> 
                <input type="hidden" id="idrowbaru" name="idrowbaru"  value="idrowbaru"/> 

                <table id="bapkasrincitable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th> 
                            <th>Nama Berita Acara</th>
                            <th>Nilai Berita Acara</th> 
                            <!--<th>Hapus</th> -->
                            <th>Pilih</th>

                        </tr>
                    </thead>

                    <tbody id="bapkasrincitablebody" >
                    </tbody> 

                    <tfoot id="bapkasrincitablefoot" >
                        <tr>
                            <th colspan="2" style="text-align:right">Total:</th>
                            <th colspan="1">
                                <input  type='text' id="totalbk"  name="totalbk" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;' disabled   />
                            </th>
                        </tr>
                    </tfoot>

                    <tfoot id="bapkasrincitablefoot2" >
                    </tfoot>

                </table>
            </div>
        </form>
    </div> 


</form:form>
<script type="text/javascript">
    function is() {
        var idskpd = document.getElementById("idskpd").value;

        if (idskpd == "761") {
            document.getElementById("hiddenDiv1").style.visibility = "visible";

        } else
        {
            document.getElementById("hiddenDiv1").style.visibility = "hidden";
        }


    }
</script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkas/editbapkas.js"></script>  
