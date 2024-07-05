<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/skpd/skpdlistpopupwilspmsp2d.js"></script>    


<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Satuan Kerja Perangkat Daerah</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" name="idskpd" id="idskpd" />
                        <input type="hidden" name="tahun" id="tahun" value="${tahunAnggaran}" />
                        <input type="hidden" name="levelSkpd" id="levelSkpd" value="${levelSkpd}" />
                        <input type="hidden" name="kodewilayah" id="kodewilayah" value="${sessionScope.pengguna.kodeProses}" />
                        <input type="text"  name="skpd"  id="skpd"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    grid()" />
                    </div>
                </div>
            </div>
         <!--   <div class="form-group">
                <label class="col-md-3 control-label">Kode SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="kodeskpd"  id="kodeskpd"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    grid()" />

                    </div>
                </div>
            </div>-->
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portlet box">
    <div class="portlet-title">
     <!--   <div class="caption"><i class="icon-cogs"></i>Satuan Kerja Perangkat Daerah</div>-->

    </div>
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
