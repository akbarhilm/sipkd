<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function() {

    });

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
    <li><a href="#">Arsip Buku Kas Umum</a></li>

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
                        <form:hidden path="idTransaksi" id='idTransaksi' value=""  />
                        <form:hidden path="bulan" id='bulan' value=""  />
                        <form:select path="bulan" id='bulan' onchange="" readonly="true" disabled="true">
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
                            <form:option value="JJ">SPJ</form:option>
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
                        <form:input type="text" path="tglPosting" id="tglPosting"  size="14" value="" readonly="true"/>
                        <form:hidden path="noJournalDok" id='noJournalDok'  />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Dok :</label>
                <div class="col-md-4">
                    <div class="input-group" id="nobuktiketerangan">
                        <form:input path="noBukti" id="noBukti" type="text"  readonly="true"  size="55" /> 
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Dokumen :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" path="tglDok" id="tglDok"  readonly="true" size="14" value="" />
                        <form:errors path="tglDok" class="error" />

                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">No Arsip :</label>
                <div class="col-md-4">
                    <div class="input-group" id="">
                        <form:input path="inboxFile" id="inboxFile" type="text"  readonly='true' class="required "  size="14" maxlength="10" /> 
                        <form:errors path="inboxFile" class="error" />

                    </div>
                </div>  
            </div>

        </div>
    </div>  

    <div id="tabelSP2D" class="portlet-body">

        <table id="sp2dtable" class="table table-striped table-bordered table-condensed table-hover " >
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

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <a class="btn blue"  href="${pageContext.request.contextPath}/bku/indexbku" >Kembali</a>
        </div>
    </div>                    
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoringbku/editbkusp2d.js"></script>  

