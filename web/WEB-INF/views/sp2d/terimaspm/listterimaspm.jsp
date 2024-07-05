<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#"> TANDA TERIMA SPM</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div> 

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Tanda Terima SPM</div>
        <!-- <div class="actions">
            <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
        </div> -->
    </div>

    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">  
            <div class="form-group"> 
                <div class="col-md-4">
                    <input type="hidden" id="codeWilSp2dproses" path="codeWilSp2dproses" name="codeWilSp2dproses" value="${pengguna.kodeProses}"  />

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-4"> 
                    <input type="text" readonly="true"  name="namwil"  id="namwil"  class="m-wrap large" size="25"  value="${namwil}" />
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" readonly="true"/> 
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridterimaspm()'>Cari</button>
                </div>
            </div>
        </form>
    </div>
</div>         

<div class="portlet box">
    <div class="portlet-title">
    </div>

    <div class="portlet-body">
        <table id="terimaspmtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th rowspan="2" style="	vertical-align: middle;">No</th>
                    <th rowspan="2" style="	vertical-align: middle;">Wilayah SP2D</th>
                    <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="nospmfilter" onkeyup="cariNoSPM()" /></th>
                    <th rowspan="2" style="	vertical-align: middle;">No Dokumen SPM</th>
                    <th rowspan="2" style="	vertical-align: middle;">Jenis</th> 
                    <th rowspan="2" style="	vertical-align: middle;">Beban</th> 
                    <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeskpdfilter" onkeyup="cari()"/></th>
                    <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaskpdfilter" onkeyup="cari2()"/></th>
                    <th rowspan="2" style="	vertical-align: middle;">Tanggal Cetak SPM</th>
                    <th rowspan="2" style="	vertical-align: middle;">Keterangan SPM</th>
                    <th rowspan="2" style="	vertical-align: middle;">Pilihan</th>
                </tr>
                <tr>
                    <th style="vertical-align: middle;">No SPM</th>
                    <th style="vertical-align: middle;">Kode SKPD</th>
                    <th style="vertical-align: middle;">Nama SKPD</th>

                </tr>
            </thead>
            <tbody id="terimaspmtablebody" >
            </tbody>                
        </table>
    </div>
</div>

<script> document.getElementById("codeWilSp2dproses").disabled = true;
</script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/terimaspm/terimaspmlist.js"></script>  

<script>
    $(document).ready(function() {
    cekwil();
    //cekNoDPAPrbh();
});
    function cekwil() {
        var codeWilSp2dproses = $("#codeWilSp2dproses").val();
            console.log("codeWilSp2dproses="+codeWilSp2dproses);
        if (codeWilSp2dproses ==1) {
            console.log("kode 1="+codeWilSp2dproses);
            var nama="Jakarta Pusat";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==2) {
            console.log("kode 2="+codeWilSp2dproses);
            var nama="Jakarta Utara";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==3) {
            console.log("kode 3="+codeWilSp2dproses);
            var nama="Jakarta Barat";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==4) {
            console.log("kode 4="+codeWilSp2dproses);
            var nama="Jakarta Selatan";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==5) {
            console.log("kode 5="+codeWilSp2dproses);
            var nama="Jakarta Timur";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==6) {
            console.log("kode 6="+codeWilSp2dproses);
            var nama="Kepulauan Seribu";
            $('#namwil').val(nama);
        }
        else if (codeWilSp2dproses ==0) {
            console.log("kode 0="+codeWilSp2dproses);
            var nama="Balaikota";
            $('#namwil').val(nama);
        }
        else {
            var nama="kode null";
            $('#namwil').val(nama);
        }
    }

</script>