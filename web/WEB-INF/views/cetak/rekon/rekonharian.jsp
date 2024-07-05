<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">Laporan Rekon Harian</a></li>
</ul>
<div class="portlet box red">
    
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Laporan Rekon Harian</div>

    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="idskpd" onchange="gridsppup(this.value)" name="idskpd" value="${idskpd}"  /> 
                        <input type="text" readonly="true" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium " /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4">
                    <input type="hidden" id="kproses" name="kproses" value="${pengguna.kodeProses}"  />
                    <input type="text" readonly="true"  name="namwil"  id="namwil"  class="m-wrap large" size="25"  value="${namwil}" />
                </div>
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!-- <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>-->
                        <input type="text"  name="tgl"  id="tgl"  class="m-wrap large date-picker entrytanggal" size="14"  value="${tgl}" /> 
                    </div>

                </div>  
            </div>

            
           
        </form>
    </div>
</div>        
<div class="portlet box">
    
    <div class="form-actions fluid">
            <div class="col-md-offset-3 col-md-9" align="left">
                <button type="button" id="prosespengesahan" class="btn blue" onclick='cetak()' >Cetak</button>
                
            </div>
        </div> 

    
    <div class="portlet-title">
    </div>
    <div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>No SP2D</th> 
                        <th>Jam Kirim</th>
                        <th>Nilai Bayar</th> 
                        <th>Kode Bayar</th> 
                        <th>Jam Bayar</th>
                        <th>Nilai Bayar (Bank)</th>
                        <th>Keterangan</th>
                        <th>Kode/Nama SKPD</th>

                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>

        
    </div>

</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/rekon/cetakrekonharian.js"></script>  
