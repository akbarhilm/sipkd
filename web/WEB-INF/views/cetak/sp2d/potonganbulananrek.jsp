<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Rekapitulasi Laporan SP2D Potongan Bulanan</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">Rekapitulasi  Laporan</a></li>
</ul>
<div class="portlet box red">
    <table id="cetakspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Rekapitulasi Laporan SP2D Potongan Bulanan</div>
       
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
         
           <div class="form-group">
                    <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="hidden" id="idskpd" onchange="gridsppup(this.value)" name="idskpd" value="${idskpd}"  /> 
                            <input type="text" readonly="true" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
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
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                       
                       <input type="text"  name="tgl"  id="tgl"  class="required  entrybulan" size="14"  value="${tgl}" /> 
                       <a class="icon-book" onclick='cetakpotbulrek()' href="#" ></a>
                    </div>
                    
                </div>  
            </div>
               <!--    <div class="form-group">
                <label class="col-md-3 control-label">Bulan:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan"     size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>  -->  
                    
                     <div class="portlet-title">
    </div>
           
         <!--  <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"  class="btn blue" onclick="cetak()" > Cetak </button>
                   
                </div>
            </div>-->
                 
        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
         <div class="portlet-body">
            <table id="cetakspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/sp2d/cetaksp2dpotbulrek.js"></script>  
