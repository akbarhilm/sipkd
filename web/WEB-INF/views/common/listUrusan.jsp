<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/urusanlistpopup.js"></script>    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/programlist.js"></script>   
<script type="text/javascript">
    $(document).ready(function() {
        tampil("http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/ref/program/json/listurusanjson");
    });
   
    function ambilurusan(id) {
        $('#idurusan', window.parent.document).val(id).change();
        $('#namaUrusan', window.parent.document).val($("#namaProgram" + id).val());
         parent.$.fancybox.close();
    }
</script>
<div class="row-fluid">
    <div class="span12">  
        <h3 class="page-title">
            Daftar URUSAN
            <small>Daftar Urusan</small>
        </h3>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <div class="portlet box blue-stripe">
            <div class="portlet box blue ">
                <div class="portlet-title">
                    <h4><i class="icon-reorder"></i>Form Pencarian</h4>
                </div>
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form action="#" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">Urusan:</label>
                            <div class="controls">
                                <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" /><a class="various fancybox.ajax" href="${pageContext.request.contextPath}/common/listUrusan" title="Pilih Urusan">&nbsp;<div class="icon-zoom-out"></div></a>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="button" onclick="tampil()" class="btn blue">Cari</button> 
                        </div>
                    </form>
                    <p>&nbsp;</p>
                    <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                        <thead>
                            <tr>
                                <th>No</th> 
                                <th>Kode</th>
                                <th>Nama</th>
                                <th>Pilih</th>
                            </tr>
                        </thead>
                        <tbody  >
                        </tbody>
                    </table> 
                </div>
            </div>

        </div> 
    </div>
</div>
