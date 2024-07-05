<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar SP2D</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="tahun"  id="tahun"   class="form-control " size="5" value="${tahunAnggaran}"/>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <select id="wil">

                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Validasi :</label>
                <div class="col-md-5">
                    <div class="input-group">
                       <input type="text"  name="tglv"  id="tglv"   class="date-picker2 " size="15" />

                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Tampil</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portlet box">
    
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar SP2D</div>

    </div>
    <div >
        <div class="top" style="float: right;margin-right:2%;margin-top: 1%">
           
                
        </div>
        
        <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                     <th rowspan="2">No</th> 
                    <th rowspan="2">ID SP2D</th>
                    <th><input type="text" id="nosp2d" onkeyup="if (event.keyCode == 13)
                        grid()" size="25" placeholder="Search By No Dokumen SP2D"/></th>
                    <th><input type="text" id="kodeskpd" onkeyup="if (event.keyCode == 13)
                        grid()" size="18" placeholder="Search By Kode SKPD"/></th>
                     <th rowspan="2">NAMA SKPD</th>
                    <th rowspan="2">NILAI SP2D</th>
                    <th rowspan="2">STAN</th>
                    <th rowspan="2">MESSAGE</th>
                    <th rowspan="2">VIEW</th>
                    <th rowspan="2">APPROVAL</th>
                </tr>
                <tr>
                   
                    <th>NO SP2D</th>
                    <th>SKPD</th>
                   
                </tr>
            </thead>
            <tbody  >
            </tbody>
            <tfoot>
            <th colspan="5">Total :</th>
            <th><input type="text" readonly="true" class="number inputmoney" id="sumfooter"></th>
            <th colspan="4"></th>
            </tfoot>
        </table> 
    </div>    
</div>
    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/eset/sp2d.js"></script>  
