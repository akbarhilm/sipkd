<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/skpd/pejabatppkdlist.js"></script>    
<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });
    function tampil() {
        grid("${pageContext.request.contextPath}/common/json/listpejabatppkdjson");
        //   grid("http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/common/json/listskpdjson");

    }

</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Pencarian Daftar Pejabat PPKD</div>
    </div>
    <div class="portlet-body">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama:</label>
                <div class="col-md-5">
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" /> 
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" onclick="tampil()" class="btn blue">Cari</button> 
                </div>
            </div>
            <input type="hidden" name="id" id="id" value="${id}" />
            <input type="hidden" name="kode" id="kode" value="${kode}" />
            <input type="hidden" name="nilai" id="nilai" value="${nilai}" /> 
        </form>

    </div>
</div>

<table id="pejabatppkdtable" class="table table-striped table-bordered table-condensed table-hover " >
    <thead>
        <tr>
            <th>No</th> 
            <th>NIP</th>
            <th>NRK</th>
            <th>Nama</th>
            <th>Jabatan</th>
            <th>Pilih</th>
        </tr>
    </thead>
    <tbody  >
    </tbody>
</table>