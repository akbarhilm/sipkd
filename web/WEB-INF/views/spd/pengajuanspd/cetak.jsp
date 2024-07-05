<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD </a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Cetak SPD</a></li>
</ul>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Cetak SPD  </div>
        <% /* <div class="actions">
             <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a>
             </div> */%>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="getlistspdcetak(this.value)" name="idskpd" value="${idskpd}"  />
                    <input type="hidden" id="levelSkpd" name="levelSkpd"  value="${levelSkpd}" />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>

                </div>
            </div>
            <% /* <div class="form-group">
                 <label class="col-md-3 control-label">Pagu BTL:</label>
                 <div class="col-md-4">
                 <div class="input-group">
                 <input type="text" name="pagubtl" id="pagubtl" class="m-wrap medium" />
                 </div>
                 </div>
                 </div>
                 <div class="form-group">
                 <label class="col-md-3 control-label">Sisa SPD:</label>
                 <div class="col-md-4">
                 <div class="input-group">
                 <input type="text" name="sisaspd" id="sisaspd"  class="m-wrap medium" />
                 </div>
                 </div>
                 </div>   */%>

        </form>
    </div>
</div>
<div class="portlet box">
    <div class="portlet-title">
    </div>
    <!--<div style="float: right;margin-bottom: 10px;">
       <input type="hidden" name="keyalamat" id="keyalamat" />
       <button type="button" id="reports" class="btn btn-defaul blue" onclick="pindahhalcetakakun()">Cetak</button>
       <div  style="display: none"><a id="popupcetakakun" href="#" class="fancybox fancybox.iframe" ></a></div>
       <input type="hidden" name="keyalamat" id="keyalamat" />
   </div>-->
    <div>
        <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>SPD No</th>
                    <th>Jenis</th>
                    <th>Tgl SPD</th>
                    <th>Nilai SPD</th>
                    <th>Status</th>
                    <th>Tgl Cetak</th>
                    <th>Pejabat TTD</th>
                    <th>Status TTD</th>
                    <th>Pilih</th>
                    <th>Unduh PDF</th>
                    <th>Batal</th>
                </tr>
            </thead>
            <tbody id="btlspdtablebody" >
            </tbody>
        </table>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/spdcetak.js"></script>   