<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Laporan Bulanan BKU</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Bulanan BKU</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idskpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="idskpd" class="label label-important" />
                    </div>
                </div>
            </div> 
               <!-- 
            <div id="labelwilayah" class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select name="wilayah" id="wilayah" onchange="">
                            
                        </select>
                    </div>
                </div>  
            </div>
              -->      
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                      
                        <select name="bulan" id="bulan" onchange="setSaldoAwal()" >
                            <option value="01">Januari</option> 
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>
                        </select>
                        
                    </div>
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislap"  onchange="setSaldoAwal(), setPanelAkun()">
                            <option value="22" >Form 22 : BKU (Tutup BKU)</option> 
                            <option value="8" >Form 23 : Bank</option> 
                            <option value="9" >Form 24 : Tunai</option> 
                            <option value="P1" >Form 25 : PPH PS 21</option> 
                            <option value="P2" >Form 25 : PPH PS 22</option> 
                            <option value="P3" >Form 25 : PPH PS 23 Jasa I</option> 
                            <option value="P4" >Form 25 : PPH PS 4 Ayat 2</option> 
                            <option value="P5" >Form 25 : PPN</option> 
                            <option value="P6" >Form 25 : PPH</option> 
                            <option value="7" >Form 26 : Panjar</option> 
                            <option value="27" >Form 27 : Buku Rincian Objek Belanja</option> 
                            <option value="28" >Form 28 : Register SPP/SPM/SP2D</option> 
                            <option value="48" >Form 48 : SPJ Belanja (A3)</option> 
                            <option value="49" >Form 49 : SPJ Belanja - Administratif (A3)</option> 
                            <option value="50" >Form 50 : SPJ Belanja - Fungsional (A3)</option> 
                            <option value="PJK" >Form : Pajak (Rekap)</option>
                            <option value="SK1" >Form Saldo Kas : Laporan Saldo Kas Beban Belanja</option>
                            
                        </select>
                    </div>
                </div>  
            </div>
                    
            <div id="labelakunbelanja" class="form-group">
                <label class="col-md-3 control-label">Akun Belanja :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="akunbelanja" name="akunbelanja">
                            
                        </select>
                    </div>
                </div>  
            </div>
                    
            <div id="saldoawallabel" class="form-group">
                <label class="col-md-3 control-label">Saldo Awal : </label>
                <div class="col-md-4">
                    <input name="saldo" id="saldo" type="text" size="20"  value="" /> 
                </div>
            </div>
                
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/formbku/formbku.js"></script>  

