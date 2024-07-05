<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Cetak Jurnal Umum SKPD</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Cetak Jurnal Umum SKPD</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            
            
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenislaporan" onchange="setjenis(this.value)">
                            <option value="1" selected>1 - Laporan Neraca</option> 
                            <option value="2">2 - Lapora Realisasi Anggaran (LRA)</option>
                            <option value="3">3 - Laporan Operasional (LO)</option>
                            <option value="4">4 - Laporan Arus Kas (LAK)</option>
                            <option value="5">5 - Laporan Perubahan Ekuitas</option>
                            <option value="6">6 - Laporan Perubahan Saldo Anggaran Lebih (SAL)</option>
                        </select>
                    </div>
                </div>  
            </div>   
                
           <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" onchange="setbulan(this.value)">
                            <option value="01" selected>01 - Januari</option> 
                            <option value="02">02 - Februari</option>
                            <option value="03">03 - Maret</option>
                            <option value="04">04 - April</option>
                            <option value="05">05 - Mei</option>
                            <option value="06">06 - Juni</option>
                            <option value="07">07 - Jul</option>
                            <option value="08">08 - Agustus</option>
                            <option value="09">09 - September</option>
                            <option value="10">10 - Oktober</option>
                            <option value="11">11 - November</option>
                            <option value="12">12 - Desember</option>
                            <option value="13">Audited</option>
                        </select>
                    </div>
                </div>  
            </div>        
        
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak</button>
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/laporanprovinsi/laporanprovinsi.js"></script>  

