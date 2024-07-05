<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ </a> 
        <span class="icon-angle-right"></span>
    </li>    
   
    <li><a href="#">Monitoring SPJ Belanja Langsung</a></li>
</ul>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Monitoring SPJ Belanja Langsung</div>
        <% /* <div class="actions">
             <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
             </div> */%>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD : </label>
                <div class="col-md-4">
                 <!--   <input type="hidden" id="idskpd" onchange="getlistspdcetak(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a><br>-->
                    
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}"  />
                    <input type="hidden" id="kodeSkpd" name="kodeSkpd" value="${skpd.kodeSkpd}"  />
                    <input type="hidden" id="level" name="level" value="${skpd.levelSkpd}"  />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
                    
                    
                </div>
            </div>
                    <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <!-- <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>-->
                       <input type="text"  name="tgl"  id="tgl"  class="m-wrap large date-picker2 entrytanggal valid" size="14"  value="${tgl}" /> 
                      <a class="fancybox fancybox.iframe icon-book" onclick='cetakmonbl()' href="#" ></a>
                      <!-- <button type="button" class="btn blue" onclick='cetaktes()' >Cek</button> --> 
                    </div>
                    
                </div> 
                      
                      
            </div>
           </form>
    </div>
</div>
<div class="portlet box">
      <div>
        <table id="btlspdtable" >
          
        </table>
    </div>
</div>
                
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/spjmonitoringbl.js"></script>   