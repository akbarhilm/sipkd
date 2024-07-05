<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });

    /*function ambilketerangan() {
     window.localStorage.setItem("keteranganKeg", "SPJ");
     //console.log("keteranganKeg = "+ketcombo);
     }*/

</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bku/indexbku" >Buku Kas Umum</a> 
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
                    <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />
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
                        <form:select path="bulan" id='bulan' onchange="" disabled="true" readonly="true" >
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

                        <form:select path="kodeTransaksi" id="kodeTransaksi" disabled="true" readonly="true" >
                            <form:option value="JO" >SP2D</form:option> 
                            <form:option value="JJ">SPJ - mencatat pengakuan belanja langsung per kegiatan dan per nomor bukti</form:option>
                            <form:option value="ST">SETORAN</form:option>
                            <form:option value="NP">NPD KE PPTK/dll</form:option>
                            <form:option value="PJ">PAJAK</form:option>
                            <form:option value="LL">Lain-lain</form:option>
                        </form:select>
                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglPosting" id="tglPosting"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" disabled="true"  />
                        <form:hidden path="noJournalDok" id='noJournalDok'  />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-4">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text"  readonly="true" size="55" /> 
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok"  class="required  date-picker2 entrytanggal2 valid" size="14" value="" disabled="true"  />
                        <form:errors path="tglDok" class="error" />

                        <input type="hidden" id="idKegpop" name="idKegpop"  onchange="getKegiatan()" value="">
                        <input type="hidden" id="kodeKegpop" name="kodeKegpop"  onchange="" value="">
                        <input type="hidden" id="namaKegpop" name="namaKegpop"  onchange="" value="">
                        <input type="hidden" id="ketKegpop" name="ketKegpop"  onchange="" value="">

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
                        <form:select path="kodePembayaran" id="kodePembayaran" onchange="setPembayaran(this.value)" disabled="true" readonly="true" >
                            <form:option value="1">1 - Tunai</form:option> 
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                            <form:option value="3">3 - Panjar</form:option>
                        </form:select>
                    </div>
                </div>  
            </div>
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <form:input type="text" path="ketKegiatan"  id="ketKegiatan"  class="m-wrap large" size="80" readOnly="true"/>  
                    <form:hidden path="idKegiatan" id='idKegiatan'  />
                    <form:hidden id="kodeKeg" path="kodeKeg"  value="" />
                </div>
            </div>
                
            <div id="labelbeban" name="labelbeban"  class="form-group">
                <label class="col-md-3 control-label">Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="beban" id='beban' value=""  />
                        <input name="bebanspj" id="bebanspj" type="text" readonly="true" size="8" />
                        
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
                    
                  
            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <form:input path="nipPptk" id="nipPptk" type="text"  class="required " readonly="true"   /> 
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <form:input path="namaPptk" id="namaPptk" type="text"  class="required " size="50" readonly="true"  /> 
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" ROWS="3" maxlength="400" readonly="true"  />
                </div>
            </div>
                
        </div>
    </div>  

    <div class="portlet box">
        <div id="tabelSPJ" class="portlet-body">

            <table id="spjtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS (BAST)</th>
                        <th>BKU Sebelum</th> 
                        <th>Sisa SPD</th>
                        <th>Nilai BKU</th>
                        
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
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!--  <button type="button" id="btncek" class="btn blue" onclick='CEK()' >CEK</button>   -->

            <button type="button" class="btn blue" onclick='update()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku/editbkuspjkoreksi.js"></script>  

