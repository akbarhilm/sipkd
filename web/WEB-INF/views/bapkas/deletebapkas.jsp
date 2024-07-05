<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
    .inputmoneyX{
        border:none;margin:0;text-align:right;width:80%;background: aliceblue;
    }
</style>

<script>
    $(document).ready(function() { 
        setBulanEdit($('#tahun').val(),$('#idskpd').val());
        
         var mm = $('#tglBkuBaformat').val().substr(4, 2);
        var dd = $('#tglBkuBaformat').val().substr(6, 2);
        var yyyy = $('#tglBkuBaformat').val().substr(0, 4);

        var tgl = dd + "/" + mm + "/" + yyyy;
        $('#tglBkuBa').val(tgl); 
       
    });
</script>

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


<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/bapkas/prosesdelete" class="form-horizontal">
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
                         <form:hidden path="idSkpdBAPKas" id='idSkpdBAPKas' />
                         <form:hidden path="skpd.idSkpd" id='idskpd'   value="${skpd.idSkpd}"  />
                      <!--  <form:input path="skpd" cssClass="required" id="skpd"   type="text" size="100" readonly='true' value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />-->
                       <form:input path="skpd.namaSkpd" id='namaskpd'  cssClass="required"   type="text" size="100" maxlength="110" readonly='true'  />
                        </div>
                    </div>
                </div>     
                <div id="hiddenDiv1" style="visibility:hidden;" class="form-group">
                    <label class="col-md-3 control-label">Kode Wilayah SP2D</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:select readonly = 'true'  path="kodeWilSp2d">
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
                        <form:input path="namaHari" id="namaHari" readonly='true' cssClass="required"   size="10"  /> 
                    </div>
                </div>
            </div>-->
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                         <form:input type="text"  path="tglBkuBa" id="tglBkuBa"  class="required  date-picker2 entrytanggal2 valid"  size="14" readonly="true" />  
                        <form:hidden path="tglBkuBaformat" id='tglBkuBaformat' /> 
                    </div>
                </div>
            </div>
                    <div class="form-group">
                <label class="col-md-3 control-label">Bulan BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" path="blnBkuBa" id='blnBkuBa' value="${bln}"/>
                        <form:input  cssClass="required" path="blnBkuBa" readonly='true' id="blnBkuBa" value="${bln}" size="15"  />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkPa" id="nrkPa"  readonly='true' size="7" maxlength="6"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipPa" id="nipPa"  readonly='true' size="20" maxlength="18"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaPa" id="namaPa" readonly='true'  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanPa" id="jabatanPa" readonly='true'  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nrkBend" id="nrkBend" readonly='true' size="7" maxlength="6"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="nipBend" id="nipBend" readonly='true' size="20" maxlength="18"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="namaBend" id="namaBend" readonly='true'  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jabatan Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="jabatanBend" id="jabatanBend" readonly='true'  size="67" maxlength="50"   />  
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="noSkGub" id="noSkGub" readonly='true' size="50" maxlength="50"   />  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SK Gubernur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="tglSkGub" id="tglSkGub"  readonly='true' size="20" maxlength="18"   />  
                    </div>
                </div>
            </div>
                    
         
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Kertas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangKertas"  id="nilaiUangKertas1" readonly='true'  class="inputmoneyX" size="19"  style="text-align:right" />&nbsp;&nbsp;&nbsp;(1) 
                       
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Uang Logam:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangLogam"  id="nilaiUangLogam1" readonly='true' class="inputmoneyX" size="19"  style="text-align:right" />&nbsp;&nbsp;&nbsp;(2)  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <form:input  type="text"  path="nilaiUangSp2d"  id="nilaiUangSp2d1" readonly='true' class="inputmoneyX" size="19"  style="text-align:right" />&nbsp;&nbsp;&nbsp;(3)    
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <form:input  type="text"  path="nilaiUangSaldoBank"  id="nilaiUangSaldoBank1" readonly='true' class="inputmoneyX" size="19"  style="text-align:right" />&nbsp;&nbsp;&nbsp;(4)  
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Surat Berharga:</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <form:input  type="text"  path="nilaiUangSuratBerharga"  id="nilaiUangSuratBerharga1" readonly='true'  class="inputmoneyX" size="19"  style="text-align:right" />&nbsp;&nbsp;&nbsp;(5) 
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Total BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text"  path="nilaiUangTotalBkuBa"  id="nilaiUangTotalBkuBa1" readonly='true' class="inputmoneyX" size="9"  style="text-align:right" />(6=1+2+3+4+5)
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Saldo BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <form:input  type="text"  path="nilaiUangSaldoBkuBa"  id="nilaiUangSaldoBkuBa1" readonly='true' class="inputmoney" size="19"  style="text-align:right" />  
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Selisih BAP Kas:</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <form:input  type="text"  path="nilaiUangSelisihBkuBa"  id="nilaiUangSelisihBkuBa1" readonly='true' class="inputmoney" size="19"  style="text-align:right" />  
                    </div>
                </div>
            </div>  
                    
                           
                  <div class="form-group">
                <label class="col-md-3 control-label">Penjelasan Perbedaan positif/negatif:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea readonly='true' cols="70" rows="3"   path="ketBkuBa" id="ketBkuBa"   />
                     </div>
                </div>
            </div>        
        
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn red" >Hapus</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/bapkas/indexbapkas">Kembali</a>
                </div>
            </div>

        </div>
    </div>  
                    <div class="portlet box">
        <form id="formasettetap">
            <div class="portlet-title">

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
                    function is(){
                    var idskpd = document.getElementById("idskpd").value;
                    
                    if(idskpd == "761"){
                        document.getElementById("hiddenDiv1").style.visibility ="visible";
                                              
                    }else
                    {
                        document.getElementById("hiddenDiv1").style.visibility ="hidden";
                    }
                    
                    
                }
                </script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bapkas/deletebapkas.js"></script>  
