<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listkegiatanpopup.js"></script>


<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs" ></i>Daftar Kegiatan</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" value="${tahunAnggaran}" name="tahun" id="tahun" maxlength="4" size="10"  class="m-wrap medium inputnumber" readonly="true" />
                        <input type="hidden" id="kodeskpd" name="kodeskpd" id="kodeskpd"  value="${skpd.kodeSkpd}"  />
                        <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}"  />

                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Kode Kegiatan :</label>
                <div class="col-md-4">
                    <input type="text"  name="kodekeg"  id="kodekeg"  />
                </div>

            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan :</label>
                <div class="col-md-4">
                    <input type="text"  name="namakeg"  id="namakeg" size="50" />
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
                    <th>Kegiatan</th>
                    <th>Nilai Anggaran</th>
                    <th>Nilai UPGUTU</th>
                    <th>Kontrak Sebelum</th>
                    <th>Kontrak Saat Ini</th>
                    <th>Setoran TU</th>
                    <th>Nilai Sisa</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table>
    </div>
</div>
