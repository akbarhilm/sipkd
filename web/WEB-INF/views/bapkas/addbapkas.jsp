nilaiUangSaldoBkuBa<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
    $(document).ready(function() {
        setBulan($('#tahun').val(), $('#idskpd').val());

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


<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/bapkas/prosessimpan2" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Berita Acara Pemerikasaan (BAP) Kas</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   value="${skpd.idSkpd}"  />
                        <form:input path="skpd" cssClass="required" id="skpd"   type="text"  readonly='true' value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />

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
                        <form:input type="text"  path="tglBkuBa" id="tglBkuBa"  class="required  date-picker entrytanggal2 valid"  size="14"  />  

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="blnBkuBa" id="blnBkuBa" name="blnBkuBa" onchange="getNilaiKas()" >  
                            <form:options   />
                        </form:select >

                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nrkPa" cssClass="required" id="nrkPa"  size="7" maxlength="6" onKeyPress="return numbersonly(this, event)"  />

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPa" cssClass="required" id="nipPa"  size="20" maxlength="18" onKeyPress="return numbersonly(this, event)"  />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="namaPa" cssClass="required" id="namaPa"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatanPa" cssClass="required" id="jabatanPa"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nrkBend" cssClass="required" id="nrkBend"  size="7" maxlength="6" onKeyPress="return numbersonly(this, event)"  />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipBend" cssClass="required" id="nipBend"  size="20" maxlength="18" onKeyPress="return numbersonly(this, event)"  />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaBend" cssClass="required" id="namaBend"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="jabatanBend" cssClass="required" id="jabatanBend"  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noSkGub" cssClass="required" id="noSkGub"  size="50" maxlength="50"   />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="tglSkGub" cssClass="required" id="tglSkGub"  size="20" maxlength="18"   />  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Kertas:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangKertas"  id="nilaiUangKertas1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(1) 
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Logam:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangLogam"  id="nilaiUangLogam1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(2)  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SP2D:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSp2d"  id="nilaiUangSp2d1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(3)  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo Bank:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBank"  id="nilaiUangSaldoBank1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()"  class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(4)  
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Surat Berharga:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSuratBerharga"  id="nilaiUangSuratBerharga1" onkeyup="getNilaiTotalBAPKas(),getNilaiTotalSelisihBAPKas()" class="inputmoneyX" size="19" value="0" style="text-align:right" />&nbsp;&nbsp;&nbsp;(5) 
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Total BAP Kas:</label> 
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangTotalBkuBa"   id="nilaiUangTotalBkuBa1"   class="inputmoneyX" size="9" readonly="true"  style="text-align:right" />(6=1+2+3+4+5)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangSaldoBkuBa"  id="nilaiUangSaldoBkuBa" value="0" class="inputmoney" size="19" onkeyup="getNilaiTotalSelisihBAPKas(),cektotalbk()"  style="text-align:right" readonly="true"  /> &nbsp;&nbsp;&nbsp; (7)
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Selisih BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group"> 
                        <form:input  type="text"  path="nilaiUangSelisihBkuBa"  id="nilaiUangSelisihBkuBa"  class="inputmoneyX" readonly="true" size="19" value="0" style="text-align:right" /> &nbsp;&nbsp;&nbsp; (8=6-7)
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Penjelasan Perbedaan positif/negatif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea  cols="70" rows="3"   path="ketBkuBa" id="ketBkuBa"  maxlength="250" />
                    </div>
                </div>
            </div> 

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >Simpan</button>
            <!--<button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan2</button> -->
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/bapkas/indexbapkas">Kembali</a>
                </div>
            </div>

            <!--
                        <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                            <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                        </div>
                    </div>
            -->
        </div>
    </div>             
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!--<button type="button" class="btn blue" onclick='submitnilai()'>Simpan</button> -->
            <!--<a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a> -->
            <button type="button" class="btn blue" onclick='tambahRow()' >Tambah Data</button>
            <!--<button type="button" class="btn blue" onclick='cetak()'>Cetak</button> -->
            <!-- <button type="button" class="btn blue" onclick='tampilCek()' >Cek</button> -->
        </div>
    </div>
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian Berita Acara Pemerikasaan (BAP) Kas</div>       
        </div>

        <div class="portlet-body">

            <input type="hidden" id="banyakrinci" name="banyakrinci"  value="banyakrinci"/> 
                        
            <table id="bapkasrincitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>Nama Berita Acara</th>
                        <th>Nilai Berita Acara</th> 
                        <th>Pilihan</th>

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
    </div>


</form:form>
<script type="text/javascript">
    $(document).ready(function() { 
});
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkas/addbapkas.js"></script>  
