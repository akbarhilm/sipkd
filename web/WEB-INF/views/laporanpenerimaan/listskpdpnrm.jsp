<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/laporanpenerimaan/listskpdpnrm.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });
    
    function tampil() {
        grid();

    }
    function ambilskpd(id) {
        $('#skpd', window.parent.document).val($("#namaSkpd" + id).val()).change();
        $('#idskpd', window.parent.document).val(id).change();
        parent.$.fancybox.close();
    }
</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Satuan Kerja Perangkat Daerah</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Kode SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="kodeskpd"  id="kodeskpd"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-5">
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