<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#tglPosting").attr('disabled', true);
        $("#tglDok").attr("disabled", true);
        $("#pengeluaran").attr("disabled", true);
    });

    function setNoBku() {
        window.localStorage.setItem("ketNoBku", $('#noBKU').val());
        
    }
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
                        <form:hidden path="kodeKoreksi" id='kodeKoreksi' />
                    </div>
                </div>
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idBku" id='idBku' value=""  />
                        <form:select path="bulan" id='bulan' onchange="keteranganBulan()" disabled="true">
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
                            <form:option value="JG" >JASA GIRO</form:option> 
                            <form:option value="JJ">SPJ</form:option>
                            <form:option value="ST">SETORAN KE PPKD (Setoran Sisa Belanja / Uang Persediaan)</form:option>
                            <form:option value="NP">NPD KE PPTK/dll</form:option>
                            <form:option value="PJ">PAJAK</form:option>
                            <form:option value="LL">Lain-lain</form:option>
                            <form:option value="SB">Sisa Belanja (khusus Beban=LS) diterima oleh Bendahara SKPD/UKPD</form:option>
                        </form:select>

                        <form:errors path="kodeTransaksi" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglPosting" id="tglPosting"  class="required  date-picker2 entrytanggal2 valid" size="14" readonly="true"   value="" />
                        <form:hidden path="noJournalDok" id='noJournalDok'  />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text" readonly="true"   size="55" /> 
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok" readonly="true"   class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <div class="input-group" id="">
                        <form:input path="inboxFile" id="inboxFile" type="text" readonly="true"   class="required "  size="14" maxlength="10" /> 
                        <form:errors path="inboxFile" class="error" />

                    </div>
                </div>  
            </div>
            
            <div id="labeljenisbayar" name="labeljenisbayar" class="form-group">
                <label class="col-md-3 control-label">Jenis Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="jenisPembayaran" id="jenisPembayaran" disabled="true">
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
                        <form:select path="kodePembayaran" id="kodePembayaran" disabled="true" >
                            <form:option value="2">2 - Bank/Transfer/Cek</form:option>
                        </form:select>
                    </div>
                </div>  
            </div>

           <div id="labelsaldojasagiro" name="labelsaldojasagiro"  class="form-group">
                <label class="col-md-3 control-label">Saldo Jasa Giro :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name="saldojasagiro" id="saldojasagiro" type="text" readonly="true"/>
                    </div>
                </div>  
            </div>
            

            <div id="labelpengeluaran" name="labelpengeluaran" class="form-group">
                <label class="col-md-3 control-label">Nilai Jasa Giro :</label>
                <div class="col-md-4">
                    <!--   <input name="pengeluaran" id="pengeluaran" type="text" class="inputmoney " style="width: 140px" /> -->
                    <input name="pengeluaran" id="pengeluaran" type="text" onchange="setformatpengeluaran(this.value)" readonly="true"   />
                    <form:hidden path="nilaiKeluar" id="nilaiKeluar"  />
                    <form:hidden path="nilaiMasuk" id="nilaiMasuk"  />
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <form:textarea path="uraian" id="uraian" class="required " cols="80" ROWS="3" maxlength="400" readonly="true"   />
                </div>
            </div>

        </div>
    </div>  


    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku/editbkujasagiro.js"></script>  

