<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    
       
</script>

<!--ul class="breadcrumb">

    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    <!--
    <li>
        <a href="${pageContext.request.contextPath}/bku/indexbku" >Buku Kas Umum - Pengeluaran</a> 
        <span class="icon-angle-right"></span>
    </li>
    ->
    <li><a href="#">Tutup Buku Kas Umum</a></li>

</ul-->
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<form:form   method="post" commandName="refbku"  id="refbku"   action="${pageContext.request.contextPath}/tutupbuku/prosessimpan2" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Kas Umum</div>   

        </div>

        <div class="portlet-body flip-scroll">
           
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan Penutupan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:hidden path="tahun" id="tahun"  value="${tahunAnggaran}" /> 
                        <form:hidden path="bulan" id="bulan" value="" /> 
                        <input name="ketBulan" id="ketBulan" type="text"  readonly='true'  value="" /> 
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Penutupan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="tglPenutupan" id='tglPenutupan' value=""  />
                        <input type="text" name="tanggal" id="tanggal" class="required date-picker2 entrytanggal valid" size="14" onchange="setTanggalTutup()" /> 

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NIP PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nipPA" id="nipPA" size="30"   /> 
                        <form:errors path="nipPA"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NRK PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nrkPA" id="nrkPA" size="30"   /> 
                        <form:errors path="nrkPA"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaPA" id="namaPA" size="30"   /> 
                        <form:errors path="namaPA"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NIP Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nipBendahara" id="nipBendahara" size="30"   /> 
                        <form:errors path="nipBendahara"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NRK Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="nrkBendahara" id="nrkBendahara" size="30"   /> 
                        <form:errors path="nrkBendahara"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  path="namaBendahara" id="namaBendahara" size="30"   /> 
                        <form:errors path="namaBendahara"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kas Terima s.d Bulan Lalu:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="kasTerimaLalu" id="kasTerimaLalu" readOnly="true" size="30" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Kas Keluar s.d Bulan Lalu:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="kasKeluarLalu" id="kasKeluarLalu" readOnly="true" size="30" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Kas Terima Bulan Ini:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="kasTerima" id="kasTerima" readOnly="true" size="30" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Kas Keluar Bulan Ini:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="kasKeluar" id="kasKeluar" readOnly="true" size="30" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Kas Saat Ini:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="kasSaatIni" id="kasSaatIni" readOnly="true" size="30" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Tunai:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="saldoTunai" id="saldoTunai" size="30" readOnly="true" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Saldo Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="saldoBank" id="saldoBank" size="30" readOnly="true" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Surat Berharga Lainnya (Panjar, dll):</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  type="text" path="saldoLain" id="saldoLain" size="30" readOnly="true" style="text-align:right;"/>  
                    </div>
                </div>  
            </div> 



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9" >
                    <button type="button" class="btn blue" onclick='simpan()'>Simpan</button>
                    <!--
                    <button type="button" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/beranda" >Kembali</a>
                    -->
                </div>
            </div>  

        </div>
    </div>  



</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/tutupbuku/tutupbukupop.js"></script>  

