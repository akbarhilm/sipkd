<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<input type="hidden" name="kodetab" id="kodetab" value="${kodetab}">
<input type="hidden" name="jenis" id="jenis" value="${jenis}">
<input type="hidden" name="idakun" id="idakun" value="${idakun}">
<input type="hidden" name="idkegiatan" id="idkegiatan" value="${idkegiatan}">
<input type="hidden" name="idskpd" id="idskpd" value="${idskpd}">
<div>
    <div class="portlet-title">
        <div class="caption">
            <i class="icon-cogs"></i>
            <c:if test="${jenis == '1'}"  > 
                <c:if test="${kodetab == 'spd'}"  > 
                    Detail SPD BTL
                </c:if>
                <c:if test="${kodetab == 'cetak'}"  > 
                     Detail SPD BTL Cetak
                </c:if>
                <c:if test="${kodetab == 'sah'}"  > 
                    Detail SPD BTL Sah
                </c:if>
            </c:if>
            <c:if test="${jenis == '2'}"  > 
                <c:if test="${kodetab == 'spd'}"  > 
                    Detail SPD BL 
                </c:if>
                <c:if test="${kodetab == 'cetak'}"  > 
                     Detail SPD BL Cetak
                </c:if>
                <c:if test="${kodetab == 'sah'}"  > 
                    Detail SPD BL Sah
                </c:if>

            </c:if>
            <c:if test="${jenis == '3'}"  > 
                <c:if test="${kodetab == 'spd'}"  > 
                    Detail SPD Biaya
                </c:if>
                <c:if test="${kodetab == 'cetak'}"  > 
                    Detail SPD Biaya Cetak
                </c:if>
                <c:if test="${kodetab == 'sah'}"  > 
                    Detail SPD Biaya Sah
                </c:if>

            </c:if>
            <c:if test="${jenis == '4'}"  > 
                <c:if test="${kodetab == 'spd'}"  > 
                    Detail SPD Bantuan
                </c:if>
                <c:if test="${kodetab == 'cetak'}"  > 
                    Detail SPD Bantuan Cetak
                </c:if>
                <c:if test="${kodetab == 'sah'}"  > 
                    Detail SPD Bantuan Sah
                </c:if>

            </c:if>

        </div>


    </div>
    <div >
        <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th> 
                    <th>Akun</th>
                    <th>Tanggal SPD</th>
                    <th>Ket SPD</th>
                    <th>Nilai SPD</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoringspd/listpopupdetailspd.js"></script>  