<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setorjukor/listjukor.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        idskpdlama = $('#idskpdlama', window.parent.document).val();
        tahunangg = $('#tahunAngg', window.parent.document).val();
        nosetor = $('#NoSetor', window.parent.document).val();
        beban = $('#beban', window.parent.document).val();
        
        tampil();
    });

    function tampil() {
        grid();
    }

    function ambilskpd(id) {
        $('#tglSetor', window.parent.document).val($("#tanggal" + id).val()).change();
        $('#noJurnal', window.parent.document).val($("#nojurnal" + id).val()).change();
        $('#kegiatan', window.parent.document).val($("#kegiatan" + id).val()).change();
        $('#idkegiatan', window.parent.document).val($("#idkeg" + id).val()).change();

        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Jurnal Koreksi</div>
        </div>

        <div class="portlet-body">

            <table  width="100%" >

                <tr><td colspan="5"  ></td></tr>


            </table>

            <div class="form-horizontal" >

                <div class="form-group">
                    <br></br>
                    <label class="col-md-4 control-label">No Jurnal :</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                            <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        
                            <input type="text"  name="nojur"  id="nojur"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                        </div>
                    </div>
                </div>

                <div class="form-group" style="display: none">
                    <label class="col-md-4 control-label">Nama Kegiatan : </label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text"  name="nama"  id="nama"  class="form-control " size="50" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                        </div>
                    </div>
                </div>

                <div class="form-actions fluid">
                    <div class="col-md-offset-4 col-md-9">
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
                        <th>Tanggal</th>
                        <th>No Jurnal</th>
                        <th>Kode Kegiatan</th>
                        <th>Nama Kegiatan</th>
                        <th>Nilai</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
</form:form>