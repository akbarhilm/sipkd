<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD </a> 
        <span class="icon-angle-right"></span>
    </li>    
   
    <li><a href="#">Laporan Monitoring SPD </a></li>
</ul>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Laporan Monitoring SPD </div>
        <% /* <div class="actions">
             <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
             </div> */%>
    </div>
    <div class="portlet-body flip-scroll" style="height:auto;">
<form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/spd/monitoringlaporan/prosessimpan" class="form-horizontal">
        
        <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                       <!-- <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="54" value=""/>-->
                       <input type="text"  name="tgl1"  id="tgl1"  readonly="true" size="14"  value="" /> 
                       S.D
                       <input type="text"  name="tgl2"  id="tgl2"  class="m-wrap large date-picker entrytanggal1"  size="14"  value="${tgl}" /> 
                      
                    </div>
                    
                </div>  
            </div>
                       <br>
    
           <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan : </label>
               <div class="col-md-4">
                               
                <select id="options"  onchange="optionCheck()">
                    <option>===: Pilih Jenis Laporan :===</option>
                    <option value="1">1.  Laporan Realisasi Rekap Pengeluaran</option>
                    <option value="2">2.  Laporan Realisasi Asisten</option>
                 <!--   <option value="3">3.  Laporan Realisasi BTL</option>
                    <option value="4">4.  Laporan Realisasi BL</option>
                    <option value="5">5.  Laporan Realisasi BTL Bantuan</option>
                    <option value="6">6.  Laporan Realisasi Biaya</option>-->
                    
                   
                </select><br><br>
              <!--  <label class="col-md-3 control-label">Jenis APBD : </label>
                <select id="options"  name="options">
                    <option> Pilih Jenis APBD </option>
                    <option value="0">1.  APBD Murni</option>
                    <option value="1">2.  APBD Perubahan</option>
              
                </select><br><br>-->
                
                <div id="hiddenDiv1" style="height:45px;width:490px;border:1px;visibility:hidden;">
               
                  <label class="col-md-3 control-label">SKPD : </label>
                  <input type="hidden" id="idskpd" onchange="getlistspdcetak(this.value)" name="idskpd" value="${idskpd}"  /> 
                  <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="30"  value="${namaskpd}" />
                  <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                   <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                   <br><br>
                </div>  
                   
             <div id="hiddenDiv2" style="height:45px;width:490px;border:1px;visibility:hidden;">     
                <label class="col-md-3 control-label">Kegiatan : </label>
               
                    <input type="hidden" id="idskpd" onchange="getlistspdcetak(this.value)" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="30"  value="${namaskpd}" />
                    <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  /> 
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a><br>
                    
                    <br><br>
             
                   
                </div>
                
                <script type="text/javascript">
                    function optionCheck(){
                    var option = document.getElementById("options").value;
                    if(option == "1"){
                        document.getElementById("hiddenDiv1").style.visibility ="hidden";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                        document.getElementById("hiddenDiv3").style.visibility ="visible";
                        document.getElementById("hiddenDiv4").style.visibility ="hidden";
                        document.getElementById("hiddenDiv5").style.visibility ="visible";
                        document.getElementById("hiddenDiv6").style.visibility ="visible";
                      
                    }
                    if(option == "2"){
                        //window.location = "http://google.com";
                          document.getElementById("hiddenDiv1").style.visibility ="hidden";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                         document.getElementById("hiddenDiv3").style.visibility ="hidden";
                        document.getElementById("hiddenDiv4").style.visibility ="visible";
                        document.getElementById("hiddenDiv5").style.visibility ="hidden";
                        document.getElementById("hiddenDiv6").style.visibility ="hidden";
                    }
                    if(option == "3"){
                        document.getElementById("hiddenDiv1").style.visibility ="visible";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                        document.getElementById("hiddenDiv5").style.visibility ="hidden";
                        document.getElementById("hiddenDiv3").style.visibility ="hidden";
                        document.getElementById("hiddenDiv4").style.visibility ="hidden";
                        document.getElementById("hiddenDiv6").style.visibility ="hidden";
                    }
                    if(option == "4"){
                         document.getElementById("hiddenDiv1").style.visibility ="visible";
                        document.getElementById("hiddenDiv2").style.visibility ="visible";
                        document.getElementById("hiddenDiv5").style.visibility ="hidden";
                        document.getElementById("hiddenDiv3").style.visibility ="hidden";
                        document.getElementById("hiddenDiv4").style.visibility ="hidden";
                        document.getElementById("hiddenDiv6").style.visibility ="hidden";
                    }
                    if(option == "5"){
                        document.getElementById("hiddenDiv1").style.visibility ="visible";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                        document.getElementById("hiddenDiv5").style.visibility ="hidden";
                        document.getElementById("hiddenDiv3").style.visibility ="hidden";
                        document.getElementById("hiddenDiv4").style.visibility ="hidden";
                        document.getElementById("hiddenDiv6").style.visibility ="hidden";
                    }
                    if(option == "6"){
                         document.getElementById("hiddenDiv1").style.visibility ="visible";
                        document.getElementById("hiddenDiv2").style.visibility ="hidden";
                        document.getElementById("hiddenDiv5").style.visibility ="hidden";
                        document.getElementById("hiddenDiv3").style.visibility ="hidden";
                        document.getElementById("hiddenDiv4").style.visibility ="hidden";
                        document.getElementById("hiddenDiv6").style.visibility ="hidden";
                    }
                    
                }
                </script>
                   
              
              </div>
           
      </div>
         
        
           
            
                       
                       
            <!-- <div class="form-actions fluid">-->
                 <div id="hiddenDiv3" style="height:25px;width:490px;border:1px;visibility:hidden;">
                    <button id="buttoninduk" onclick='gridspdmonlaplist()' type="submit" class="btn blue" >Proses</button>
                 </div> 
                 <div id="hiddenDiv4" style="height:25px;width:490px;border:1px;visibility:hidden;">
                    <button id="buttoninduk" onclick='cetakmonlap1()' type="submit" class="btn blue" >Cetak Laporan Asisten</button>
                 </div> 
                 
                 
                <!--<div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onclick='gridspdmonlaplist()' type="submit" class="btn blue" >Proses</button>
                    <button id="buttoninduk" onclick='gridspdmonlaplist1()' type="submit" class="btn blue" >Cetak Laporan Asisten</button>
                  
                </div>
            </div> -->          
                       
          
    </div>
</div>
</form:form>                    
<div class="portlet box">
      <div id="hiddenDiv5" style="height:25px;width:490px;border:1px;visibility:hidden;">
        <table id="btlspdtable1" >
             <a class="icon-book"  onclick='cetakmonlap()' href="#" ></a>
        </table>
      </div>   
      <div id="hiddenDiv6" style="height:555px;width:1300px;border:1px;visibility:hidden;"> 
          <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
    <thead>
        <tr>
            <th>No</th>
            <th>Akun</th>
            <th>Nama Akun</th>
            <th>Anggaran</th>
            <th>SPD</th>
            <th>%</th>
            <th>SP2D</th>
            <th>%</th>
            <th>%</th>               
            
        </tr>
        <tr>
            <th></th>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5 = 4 : 3</th>
            <th>6</th>
            <th>7 = 6 : 4</th>
            <th>8 = 6 : 3</th>
            
        </tr>
    </thead>
    <tbody id="btlspdtablebody" >
    </tbody> 
</table> 
      </div> 
    </div>
</div>
                
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/laporanml.js"></script>   