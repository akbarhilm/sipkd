<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listmetodepopup.js"></script>    
 

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Metode</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Metode:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"   name="namametode"  id="namametode"   class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                grid()" />
                        </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
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
                        <th>Kode Metode</th>
                        <th>Keterangan</th>
                        <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
