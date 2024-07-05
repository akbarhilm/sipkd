<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/skpd/skpdlistpopup.js"></script>    
<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });
    /*$(document).keyup(function(e) {
     if (e.which == 10 || e.which == 13) {
     tampil();
     }
     });*/
    function tampil() {
        grid("http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/common/json/listskpdbtlbantuanjson");

    }
    function ambilskpd(id) {
        $('#skpd', window.parent.document).val($("#namaSkpd" + id).val());
        $('#idskpd', window.parent.document).val(id).change();
        $('#pagubtl', window.parent.document).val(0);
        $('#sisaspd', window.parent.document).val(0);

        parent.$.fancybox.close();
    }
</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Satuan Kerja Perangkat Daerah</div>

    </div>
   <div class="portlet-body flip-scroll">
        <div class="form-inline" >
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  name="skpd"  id="skpd"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='tampil()'>Cari</button>
                </div>
            </div>            
        </div>
    </div>
</div>
<div class="portlet box">
    
    <div >
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