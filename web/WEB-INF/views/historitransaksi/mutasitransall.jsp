<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/historitransaksi/mutasiall.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Histori Transaksi</a>
    </li>
</ul>
<form:form   method="post" commandName="progcmd"  id="progcmd"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">

    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Histori Transaksi</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <input  readonly="true"  id='tahun' size="4" value="${tahunAnggaran}"/>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Wilayah :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <form:input path="namaWilayah"  readonly="true"  id='namaWilayah'/>

                           </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Rekening :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                       <form:input path="noRekening"  readonly="true" id="noRekening"/>
             
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama Rekening :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                             <form:input path="namaRekening"  readonly="true" size="50" id="namaRekening"/>
                         
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Tanggal Awal :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                             <input    id="tglawal" />
                             
                            </div>
                             
                        </div>
        
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Tanggal Akhir :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                             <input   id="tglakhir" />
                             
                            </div>
                             
                        </div>
        
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Saldo Akhir :</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <input type="text"   readonly="true" id="saldoakhir" size="25"/>



                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <a hre="#" id="tmp" class='btn blue' onclick="cek()">Tampil</a>
                            <a href="#" class="btn dark" onclick='cetak()'>Cetak PDF</a>
                            <a href="#" class="btn dark" onclick='cetakxls()'>Cetak Excel</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
    <div class="portlet box">

        <div >
            <!--        <div style="float: right;margin-bottom: 10px;">

               &nbsp;&nbsp;<button type="button"class="btn btn-defaul blue" onclick='cetak()'>Cetak</button>

           </div>-->
            <table id="usertable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tanggal Transaksi</th>
                        <th>Jam Transaksi</th>
                        <th>Keterangan</th>
                        <th>Terima</th>
                        <th>Keluar</th>
                        <th>Ditampilkan Pada</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="4" style="text-align:right">Total:</th>

                        <th >
                            <input type='text' id="sumterima"   name="sumterima" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>

                        <th >
                            <input type='text' id="sumkeluar"   name="sumkeluar" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>


                </tfoot>
            </table>
        </div>

        <!--                <div>
                            <table  class="table table-striped table-bordered table-condensed table-hover " >
                              <tbody>

                              </tbody>
                            </table>

                    </div>-->
    </div>
</form:form>
