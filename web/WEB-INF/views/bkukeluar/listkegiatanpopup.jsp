<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkukeluar/listkegiatanpopup.js"></script>    

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs" ></i>Daftar Kegiatan</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Kegiatan:</label>
                <div class="col-md-4">
                    <input type="hidden" id="tahun" name="tahun" value="${tahunAnggaran}"  />
                    <input type="hidden" id="idskpd" name="idskpd"   />
                    <!--input type="hidden" id="kodewilayahproses" name="kodewilayahproses"   /-->
                    <input type="text"  name="kodekegiatan"   id="kodekegiatan"  class="m-wrap large" size="40"   />
                </div>     

            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="namakegiatan" id="namakegiatan"  size="40"  class="m-wrap large" /> 
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
                    <th>Kode Kegiatan</th>
                    <!--  <th>Nomor Spd</th>-->
                    <th>Nama Kegiatan</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
