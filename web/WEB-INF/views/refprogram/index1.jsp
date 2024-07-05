<div class="row-fluid">
    <div class="span12">  
        <h3 class="page-title">
            Daftar PROGRAM
            <small>Data Referensi PROGRAM</small>
        </h3>
        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="${pageContext.request.contextPath}/beranda">SPD</a> 
                <span class="icon-angle-right"></span>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ref/program/listprogram1">Referensi</a>
                <span class="icon-angle-right"></span>
            </li>
            <li><a href="#">Program</a></li>
        </ul>

    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <div class="portlet box blue-stripe">
            <div class="portlet box blue ">
                <div class="portlet-title">
                    <h4><i class="icon-reorder"></i>Form Data Program</h4>
                    <div class="actions">
                        <a href="${pageContext.request.contextPath}/spd/pengajuan/btl/add" class="btn green"><i class="icon-plus"></i> Tambah</a> 
                    </div>
                </div>
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form action="#" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">Urusan:</label>
                            <div class="controls">
                                <input type="hidden" id="idurusan"  name="idskpd"  onchange="getlistprogram(this.value)" /> 
                                <input type="text"  name="namaUrusan"  id="namaUrusan"  class="m-wrap large" />
                                <a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listUrusan?target='_top'" title="Pilih Urusan"  ><i class="icon-zoom-in"></i> Pilih</a>

                            </div>
                        </div>
                        <!--div class="control-group">
                            <label class="control-label">Pagu BTL:</label>
                            <div class="controls">
                                <input type="text" name="pagubtl" id="pagubtl" class="m-wrap medium" /> 
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Sisa SPD:</label>
                            <div class="controls">
                                <input type="text" name="sisaspd" id="sisaspd"  class="m-wrap medium" /> 
                            </div>
                        </div-->
                    </form>
                    <!-- END FORM-->
                </div> 
            </div>
            <p>&nbsp;</p>
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Urusan</th>
                        <th>Kode Program</th>
                        <th>Nama Program</th>
                        <th>Pilihan</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                </tbody>
            </table>
        </div> 
    </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/programlist.js"></script>  
<script type="text/javascript">
                                    $(document).ready(function() {
                                        $(".fancybox").fancybox({
                                            fitToView: true,
                                            autoSize: true,
                                            closeClick: true,
                                            openEffect: 'swing',
                                            closeEffect: 'swing',
                                            headers: {'X-fancyBox': true}
                                        });

                                    });
                                    function getlistprogram(id) {
                                        /*console.log(" id = " + id)
                                         $.getJSON("${pageContext.request.contextPath}/common/json/getpagudansisa/" + id,
                                         function(result) {
                                         $('#pagubtl').val(accounting.formatNumber(result.pagu));
                                         $('#sisaspd').val(accounting.formatNumber(result.vspd));
                                         });
                                         
                                         gridprogrammasterlist("${pageContext.request.contextPath}/spd/pengajuan/btl/json/getlistspd","${pageContext.request.contextPath}/spd/pengajuan/btl/edit","${pageContext.request.contextPath}/spd/pengajuan/btl/edit");
                                         */
                                         gridprogramlist("http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/ref/program/json/listprogramjson")
       
                                    }
</script>