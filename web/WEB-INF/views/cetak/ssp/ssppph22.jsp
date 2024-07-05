<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SSP</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#">SSP PPh 22</a></li>
</ul>
<div class="portlet box red">
    <table id="cetakspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <tbody id="btlspdtablebody" >
                </tbody>                
            </table>
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak SSP PPh 22</div>
       
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="gridsppup(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="hidden" id="levelSkpd" name="levelSkpd"  value="${levelSkpd}" />
                    <input type="hidden" id="kproses" name="kproses" value="${pengguna.kodeProses}"  />
                    <input type="hidden" id="user" name="user" value="${pengguna.namaPengguna}"  />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                   <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpdwil?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
         
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
             <!-- <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>
                       <input type="text"  name="tgl"  id="tgl"  class="m-wrap large date-picker entrytanggal" size="14"  value="${tgl}" /> 
                      
                    </div>
                    
                </div>  
            </div>-->
                       
                       <div class="form-group">
                <label class="col-md-3 control-label">No SP2D :</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <!-- <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>-->
                       <input type="text" class="required" name="nsp2d"  id="nsp2d"  size="20"  value="${nsp2d}" /> 
                       <a class="icon-book" onclick='cetakssppph22()' href="#" ></a>
                    </div>
                    
                </div>  
            </div> 
                    
                     <div class="portlet-title">
    </div>
           

                 
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/cetak/ssp/ssppph22.js"></script>  
