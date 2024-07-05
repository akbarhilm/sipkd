<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/skin-bootstrap/ui.fancytree.min.css" />
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/fancytree/dist/jquery.fancytree.table.js"></script>
<script src="${pageContext.request.contextPath}/static/js/aplikasi/draftpajakonline/dpo.js"></script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#"  >Draft Pajak Online</a>
    </li>
</ul>
<form:form   method="post" commandName="progcmd"  id="progcmd"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">

    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Draft Pajak Online</div>
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
                    <label class="col-md-3 control-label">Tanggal SP2D :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <input   id='tanggal' class="date-picker2"/>

                           </div>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Wilayah :</label>
                    <div class="col-md-5">
                        <div class="input-group">

                            <form:input path="namaWilayah" readonly="true"  id='namaWilayah'/>

                           </div>
                        </div>
                    </div>
                   
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
<!--                            <a hre="#" id="tmp" class='btn blue' onclick="cek()">Tampil</a>
                            <a href="#" class="btn dark" onclick='cetak()'>Cetak PDF</a>-->
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
           
        </div>

        <!--                <div>
                            <table  class="table table-striped table-bordered table-condensed table-hover " >
                              <tbody>

                              </tbody>
                            </table>

                    </div>-->
    </div>
</form:form>
