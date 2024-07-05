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

        //window.localStorage.setItem("ketBeban", $("#beban").val());
    }

    function ketsaldoawal() {
       // window.localStorage.setItem("ketSaldoAwal", ketSaldoAwal);
    }


</script>

<ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bkuspjpajak/indexbku" >Buku Kas Umum - Pengeluaran</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">SPJ dengan Pajak</a></li>

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
                    <input type="hidden" id="totalpajakhidden" name="totalpajakhidden"  />
                    
                    <input type="hidden" id="idbastemp" name="idbastemp"  />
                    <input type="hidden" id="indextemp" name="indextemp"  />
                    
                    <input type="hidden" id="noBKU" name="noBKU" value="-99" />

                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
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
                <label id="labeltglpost" class="col-md-3 control-label">Tanggal Transaksi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" />
                        <input type="hidden" id="tglHide" class="required date-picker2 entrytanggal " value=""/>
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label id="labelnobuktidok" class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-9">
                    <div class="input-group" id="nobuktiketerangan">
                        <input name="noBuktiDok" id="noBuktiDok" type="text" />

                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label id="labeltgldok" class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="idspd"  value="" onchange=""/>
                        <input type="hidden" id="nosetor"  value="" onchange="setgridst()"/>
                        <input type="text" name="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
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

            <div id="labelcarabayar" name="labelcarabayar" class="form-group">
                <label class="col-md-3 control-label">Cara Pembayaran :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="kodePembayaran" id="kodePembayaran" onchange="getNilaiSisaSpj()" >
                            <option value="1">1 - Tunai</option> 
                            <option value="2" selected>2 - Bank/Transfer/Cek</option>  
                        </select>
                    </div>
                </div> 
            </div>

            <div id="labelkegiatanspj" class="form-group">
                <label class="col-md-3 control-label">Kegiatan :</label>
                <div class="col-md-9">
                    <input type="text" name="keteranganKegPop"  id="keteranganKegPop"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihKegSpj" onclick="ambilketerangan()" href="${pageContext.request.contextPath}/bkuspjpajak/listkegiatan?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <input type="hidden" id="idKegiatanSpj" name="idKegiatanSpj"  value="">
                    <input type="hidden" id="kodeKegSpj" name="kodeKegSpj"  value="">
                </div>
            </div>


            <div id="labelbeban" name="labelbeban"  class="form-group">
                <label class="col-md-3 control-label">Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select id="beban" name="beban">
                            <option value="UP">UP/GU</option>
                            <option value="TU">TU</option>
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

            <div id="labelnippptk" name="labelnippptk" class="form-group">
                <label id="textNipPptk" class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <input name="nipPptk" id="nipPptk" type="text" maxlength="18" />
                </div>
            </div>

            <div id="labelnamapptk" name="labelnamapptk" class="form-group">
                <label id="textNamaPptk" class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <input name="namaPptk" id="namaPptk" type="text" size="50" maxlength="50"/>
                </div>
            </div>

            <div id="labeluraian" name="labeluraian" class="form-group">
                <label class="col-md-3 control-label">Uraian :</label>
                <div class="col-md-4">
                    <!-- <input name="uraian" id="uraian" type="text" size="50" /> -->
                    <TEXTAREA name="uraian" id="uraian" cols="80" ROWS="3" maxlength="400"></TEXTAREA>
                </div>
            </div>
                    
            
        </div>
    </div>  

    <div id="mygrid" class="portlet box">

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
                        <th>Pajak</th>
                        <th>Nilai Pajak</th>
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
                        <th colspan="1" >
                            
                        </th>
                        <th colspan="1" >
                            <input type='text' id="sumpajak"   name="sumpajak" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                
            </table>
        </div>
        
    </div>
                
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/bkuspjpajak/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkuspjpajak/addbku.js"></script>  

