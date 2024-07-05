<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Bendahara</a></li>
</ul>

<<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Bendahara</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <input type="text"  name="namaskpd"  id="namaskpd"  class="m-wrap large" size="40"  value="${kodeskpd}" />

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bendahara:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" name="namabendahara" id="namabendahara" maxlength="4" size="50"  class="m-wrap medium " /> 
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                </div>
            </div>

        </form>
    </div>
</div>        


<div class="portlet box">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Bendahara</div> 
    </div>
    <div >
        <table id="basttable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th> 
                    <th>NIP</th> 
                    <th>Nama </th> 
                    <th>SKPD</th> 
                    <th>Nama Jabatan</th>  
                    <th>Pilih</th> 
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listbendahara.js"></script>  