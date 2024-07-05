<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Monitoring SPD</a> 
        <span class="icon-angle-right"></span>
    </li>    
</ul> 
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar  Monitoring SPD</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  readOnly="true"/> 
                    </div>
                </div> 
            </div>
        </form>
    </div>
</div>     

<div class="portlet box red tabbable">
    
    <div class="portlet-body">
        <div class=" portlet-tabs">
            <ul class="nav nav-tabs">
                <li class=""><a href="#portlet_tab4" data-toggle="tab">Bantuan</a></li>
                <li class=""><a href="#portlet_tab3" data-toggle="tab">Biaya</a></li>
                <li class="active"><a href="#portlet_tab2" data-toggle="tab">BL</a></li>
                <li class=""><a href="#portlet_tab1" data-toggle="tab">BTL</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane" id="portlet_tab1">
                   
                <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpdbtl" name="idskpdbtl" onchange="gridspdbantuan()"  />
                    <input type="text"  name="namaskpdbtl"  id="namaskpdbtl"  class="m-wrap large" size="70"  readOnly="true"/>
                   <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/monitoringspd/listskpdbtlpopup?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                    </div>
                </div>
               

            </form>
        </div>
                    
                    
                    
         <form id="formpagusppgupx">
            <div class="portlet-title">

            </div>
            <div class="portlet-body">
                <table id="spdbtl" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Kode Akun </th>
                            <th>Nama Akun</th>
                            <th>Anggaran</th>
                            <th>Nilai SPD</th> 
                            <th>Nilai SPD Cetak</th> 
                            <th>Nilai SPD Sah</th>
                            <th>Sisa Nilai</th>
                        </tr>
                    </thead>
                    <tbody >
                    </tbody>                
                </table>
            </div>
        </form>           
                    
                    
                    
                    
                    
                
                
                
                
                </div>
                    <div class="tab-pane active" id="portlet_tab2">

                        <div class="portlet-body flip-scroll">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Nama SKPD:</label>
                                    <div class="col-md-6">
                                        <input type="hidden" id="idskpdbl" name="idskpdbl" onchange="gridspdbantuan()"  />
                                        <input type="text"  name="namaSkpdbl"  id="namaSkpdbl"  class="m-wrap large" size="50" readonly="true"  />&nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green"  href="${pageContext.request.contextPath}/monitoringspd/listskpdblpopup?target='_top" title="Pilih SKPD Koordinator"  ><i class="icon-zoom-in"></i> Pilih</a> 
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <table id="spdbl" class="table table-striped table-bordered table-condensed table-hover " >
                            <thead>
                                <tr>
                                    <th rowspan="2">No</th>
                                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodekegiatanfilter" /></th>
                                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="namakegiatanfilter" /></th>
                                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodeakunfilter" /></th>
                                    <th><input type="text"  style="border:none;margin:0;width:98%;" id="namaakunfilter" /></th>

                                    <th rowspan="2">Anggaran</th>
                                    <th rowspan="2">Nilai SPD</th>
                                    <th rowspan="2">Nilai SPD Cetak</th>
                                    <th rowspan="2">Nilai SPD Sah</th>
                                    <th rowspan="2">Sisa Nilai</th>
                                </tr>
                                <tr>
                                    <th>Kode Kegiatan</th>
                                    <th>Nama Kegiatan</th>
                                    <th>Kode Akun</th>
                                    <th>Nama Akun</th>

                                </tr>
                            </thead>
                            <tbody id="btllssp2dtablebody">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="tab-pane" id="portlet_tab3">
                    <!-- SPD BIAYA GRID DARI SINI -->

                    <div class="portlet-body flip-scroll">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Nama SKPD:</label>
                                <div class="col-md-4">
                                    <input type="hidden" id="idskpd" name="idskpd" value="${idskpdbiaya}"  />
                                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="90"  value="${kodeskpdbiaya} / ${namaskpdbiaya}" readOnly="true"  />
                                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <form id="formpagusppgup">
                            <div class="portlet-title">

                            </div>
                            <div class="portlet-body">
                                <table id="spdbiaya" class="table table-striped table-bordered table-condensed table-hover " >
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Kode Akun</th>
                                            <th>Nama Akun</th>
                                            <th>Nilai Anggaran</th> 
                                            <th>Nilai SPD</th>
                                            <th>Nilai SPD Cetak</th>
                                            <th>Nilai SPD Sah</th>
                                            <th>Nilai Sisa</th>
                                        </tr>
                                    </thead>
                                    <tbody id="btlspdtablebody" >
                                    </tbody>                
                                </table>
                            </div>
                        </form>


                        <!-- SAMPE SINI -->
                    </div>
                    <div class="tab-pane" id="portlet_tab4">
                        <!-- Dari sini BANTUAN -->
                        <div class="portlet-body flip-scroll">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Nama SKPD Koordinator:</label>
                                    <div class="col-md-6">
                                        <input type="hidden" id="idskpdkoor" name="idskpdkoor" onchange="gridspdbantuan()"  />
                                        <input type="text"  name="namaSkpdKoor"  id="namaSkpdKoor"  class="m-wrap large" size="50" readonly="true"  />&nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green"  href="${pageContext.request.contextPath}/monitoringspd/listskpdkoorpopup?target='_top" title="Pilih SKPD Koordinator"  ><i class="icon-zoom-in"></i> Pilih</a> 
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="portlet box">
                        <form id="formpagusppgup">
                            <div class="portlet-title">

                            </div>
                            <div class="portlet-body">
                                <table id="spdbantuan" class="table table-striped table-bordered table-condensed table-hover " >
                                    <thead>
                                        <tr>
                                            <th rowspan="2">No</th>
                                            <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodeakunfilterbantuan" /></th>
                                            <th><input type="text"  style="border:none;margin:0;width:98%;" id="namaakunfilterbantuan" /></th>
                                            <th><input type="text"  style="border:none;margin:0;width:98%;" id="kodekegiatanfilterbantuan"  /></th>
                                            <th><input type="text"  style="border:none;margin:0;width:98%;" id="namakegiatanfilterbantuan" /></th>
                                            <th rowspan="2">Nilai Anggaran</th> 
                                            <th rowspan="2">Nilai SPD</th>
                                            <th rowspan="2">Nilai SPD Cetak</th>
                                            <th rowspan="2">Nilai SPD Sah</th>
                                            <th rowspan="2">Nilai Sisa</th>
                                        </tr>
                                        <tr> 
                                            <th>Kode Akun</th>
                                            <th>Nama Akun</th>
                                            <th>Kode Kegiatan</th>
                                            <th>Nama Kegiatan</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>                
                                </table>
                            </div>
                        </form>
                    </div>
                    <!-- Sampai sini -->
                </div>
            </div>
        </div>
    </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoringspd/monitoringspd.js"></script>  
