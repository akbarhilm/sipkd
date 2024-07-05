<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#"> Monitoring Pajak SP2D </a></li>
</ul>
<div class="portlet box red">
    <!--  <div class="portlet-title">
          <div class="caption"><i class="icon-cogs"></i>Register  SP2D</div>
          <div class="actions">
              <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a>
          </div>
      </div>-->
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <%-- <div class="form-group">
                <label class="col-md-4 control-label">Nama SKPD:</label>
                <div class="col-md-8">
                    <input type="hidden" id="idskpd" onchange="gridpajak()" name="idskpd" value="${idskpd}"  />
                    <input type="hidden" id="levelSkpd" name="levelSkpd"  value="${levelSkpd}" />

                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="70"  value="${namaskpd}"  readonly="true" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpdall?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div> 
            </div> --%>
            <div class="form-group">
                <label class="col-md-4 control-label">Tahun:</label>
                <div class="col-md-8">
                    <div class="input-group">
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" readonly="true" />
                        <input type="hidden" id="kproses" name="kproses" value="${pengguna.kodeProses}"  />
                        <input type="hidden" id="tahun"  name="tahun" value="${tahunAnggaran}"  />
                        <input type="hidden" id="idindex"  name="idindex" />
                    </div>
                </div>
            </div>

            <div id="" class="form-group">
                <label class="col-md-4 control-label">Tanggal SP2D Sah :</label>
                <div class="col-md-8">
                    <div class="input-group">
                        <select name="tglsah" id="tglsah" onchange="gridpajak()" >

                        </select>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="portlet box">

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <!-- <button type="button" id="prosespengesahan" class="btn blue" onclick='submitvalidasi()' >Proses Pengesahan</button> -->
            <button type="button" id="prosestransfer" class="btn blue" onclick='cetak()' >Cetak</button>

        </div>
    </div>
    <!--
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnTes" class="btn blue" onclick='inquirynpwpdjp()' >Tes</button>

        </div>
    </div>
    -->

    <div class="portlet-title">
    </div>
    <div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                
                <thead>
                    <tr>
                        <th rowspan="2" style="	vertical-align: middle;">No</th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="kodeskpdfilter" onkeyup="cari(this.id)" /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="namaskpdfilter" onkeyup="cari(this.id)" /></th>
                        <th style="width: 8%"><input type="text"  style="border:none;margin:0;width:98%;" id="nosp2dfilter" onkeyup="cari(this.id)" /></th>
                        <th rowspan="2" style="	vertical-align: middle;">Tgl SP2D</th>
                        <th rowspan="2" style="	vertical-align: middle;">Jenis Pajak</th>
                        <th rowspan="2" style="	vertical-align: middle;">Nilai Pajak</th>
                        <th rowspan="2" style="	vertical-align: middle;">ID Billing</th>
                        <th rowspan="2" style="	vertical-align: middle;">NTB</th>
                        <th rowspan="2" style="	vertical-align: middle;">NTPN</th>
                       
                    </tr>
                    <tr>
                        <th style="vertical-align: middle;">Kode SKPD</th>
                        <th style="vertical-align: middle;">Nama SKPD</th>
                        <th style="vertical-align: middle;">No SP2D</th>

                    </tr>
                </thead>

                <tbody id="btlspdtablebody" >
                </tbody>
            </table>
        </div>


    </div>

</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/pajaktransfer/montransfer.js"></script>
